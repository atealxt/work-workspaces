package articleanalyzer;

public interface ArtileAnalyzer {

    /** 生成索引 */
    void index();

    /** 统计文档 */
    void statistics();

    /** 分析统计结果 */
    void analyzeResult();
}
