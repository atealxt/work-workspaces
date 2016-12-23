import java.io.IOException;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/** 
 *  MR read dataset file, list top K users whose have most posts.
 *  Dataset file format:
 *      ID NAME SID TIME CONTENT
 *      ID NAME SID TIME CONTENT
 */
public class Weibo {

	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

		private final static IntWritable one = new IntWritable(1);
		private final Text word = new Text();

		@Override
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			StringTokenizer itr = new StringTokenizer(value.toString());
			if (itr.hasMoreTokens()) {
				word.set(itr.nextToken());
				context.write(word, one);
			}
		}
	}

	public static int K = 10;

	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

		private final TreeMap<Integer, Text> topK = new TreeMap<Integer, Text>();

		private final IntWritable result = new IntWritable();

		@Override
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			topK.put(sum, new Text(key));
			if (topK.size() > K) {
				topK.remove(topK.firstKey());
			}
		}

		@Override
		protected void cleanup(Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
			for (Entry<Integer, Text> t : topK.descendingMap().entrySet()) {
				result.set(t.getKey());
				context.write(t.getValue(), result);
			}
			super.cleanup(context);
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "weibo");
		job.setJarByClass(Weibo.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		if (args.length > 2) {
			K = Integer.parseInt(args[2]);
		}
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
