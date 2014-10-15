package com.multicache4j.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.digester.Digester;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.multicache4j.config.model.KeyMappingItem;
import com.multicache4j.config.model.RemoteSourceItem;

public final class ConfigReader {
    private static Logger log = Logger.getLogger(ConfigReader.class);
    public static String CONFIG_FILE = "/multicache4j.xml";
    private static ConfigReader _instance;

	// 数据源配置: name -> RemoteSourceItem
    Map<String, RemoteSourceItem> remoteSourceMap = new HashMap<String, RemoteSourceItem>();
    
    // 映射配置：pattern -> KeyMappingItem
	Map<String, KeyMappingItem> keyMap = new HashMap<String, KeyMappingItem>();
    
	// 默认映射
    String remoteSourceDefault = null;
    String localSourceDefault = null;

    private ConfigReader() {
        try {
            this.init(getClass().getResourceAsStream(CONFIG_FILE));
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace(System.out);
            } else {
                log.error("init config file " + CONFIG_FILE + " error: " + e.getMessage());
            }
        }
    }

    public static ConfigReader getInstance() {
        if (_instance == null) {
            synchronized (ConfigReader.class) {
                if (_instance == null) {
                	_instance = new ConfigReader();
                }
            }
        }
        return _instance;
    }

    @SuppressWarnings("unchecked")
	private void init(InputStream configFile) {
        Digester digester = new Digester();
        digester.setValidating(false);
        digester.addObjectCreate("root", ArrayList.class);

        // 初始化RemoteSourceItem配置
        digester.addObjectCreate("root/remotecache/", ArrayList.class.getName());
        digester.addObjectCreate("root/remotecache/datasource", RemoteSourceItem.class);
        digester.addSetProperties("root/remotecache/datasource");
        digester.addSetNext("root/remotecache/datasource", "add");
        digester.addSetNext("root/remotecache/", "add");

        // 初始化KeyPatternItem配置
        digester.addObjectCreate("root/key/", ArrayList.class.getName());
        digester.addObjectCreate("root/key/mapping", KeyMappingItem.class);
        digester.addSetProperties("root/key/mapping");
        digester.addSetNext("root/key/mapping", "add");
        digester.addSetNext("root/key/", "add");
        
        try {
            Object root = digester.parse(configFile);
            if (root != null && root instanceof ArrayList) {
                List<List> list = (List) root;
                if (CollectionUtils.isEmpty(list)) {
                    return;
                }
                for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                    List arrayList = (List) iterator.next();
                    if (CollectionUtils.isNotEmpty(arrayList)) {
                        for (Iterator itemIterator = arrayList.iterator(); itemIterator.hasNext();) {
                            Object item = itemIterator.next();
                            if (item != null) {
                                if (item instanceof RemoteSourceItem) {
                                	addRemoteSourceItem((RemoteSourceItem) item);
                                    continue;
                                }
                                if (item instanceof KeyMappingItem) {
                                	addKeyMappingItem((KeyMappingItem) item);
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace(System.err);
            } else {
                log.error("init error: " + e.getMessage());
            }
        } catch (SAXException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace(System.err);
            } else {
                log.error("init parse fail : " + e.getMessage());
            }
        }
    }
    
    // (1) 初始化RemoteSourceItem
    private void addRemoteSourceItem(RemoteSourceItem remoteSourceItem) {
        if (remoteSourceItem != null && StringUtils.isNotBlank(remoteSourceItem.getName())) {
            if (!remoteSourceMap.containsKey(remoteSourceItem.getName())) {
            	log.info("初始化RemoteCache: " + remoteSourceItem.getName());
            	remoteSourceMap.put(remoteSourceItem.getName(), remoteSourceItem);
            } else {
                log.error("重复的RemoteCache: " + remoteSourceItem.getName() + "");
            }
        }
    }
    
    // (2) 初始化Pattern
    private void addKeyMappingItem(KeyMappingItem keyMappingItem) {
        if (keyMappingItem != null && StringUtils.isNotBlank(keyMappingItem.getPattern())) {
        	if (keyMappingItem.getPattern().equals("default")) {

        		// 远程缓存默认配置
        		String remoteSource = keyMappingItem.getRemotecache();
        		if (StringUtils.isBlank(remoteSource)) {
        			
        			log.error("未设置：remoteSourceDefault=" + remoteSource);
        			
        		} else if (remoteSourceMap.containsKey(remoteSource)) {
        			
        			log.info("设置映射：remoteSourceDefault=" + remoteSource);
                	remoteSourceDefault = remoteSource;
                	
        		} else {
                	log.error("未找到：remoteSourceDefault=" + remoteSource); 
        		}
        		
        		// 本地缓存默认配置
        		String localSource = keyMappingItem.getLocalcache();
        		if (StringUtils.isBlank(localSource)) {
        			
        			log.error("未设置：localSourceDefault=" + localSource);
        			
        		} else {
        			
        			log.info("设置映射：localSourceDefault=" + localSource);
                	localSourceDefault = localSource;
                	
        		}

        	} else if (!keyMap.containsKey(keyMappingItem.getPattern())) {
        		String pattern = keyMappingItem.getPattern();
        		String remoteSource = keyMappingItem.getRemotecache();
        		String localSource = keyMappingItem.getLocalcache();
        		
        		// 校验
        		if (StringUtils.isNotBlank(remoteSource) && !remoteSourceMap.containsKey(remoteSource)) {
        			log.info("未找到：pattern=" + pattern + "; remoteSource=" + remoteSource);
        		} else {
        			log.info("设置映射：pattern=" + pattern + "; remoteSource=" + remoteSource + "; localSource=" + localSource);
        			keyMap.put(keyMappingItem.getPattern(), keyMappingItem);
        		}
        		
            } else {
                log.error("重复的pattern  " + keyMappingItem.getPattern());
            }
        }
    }

    
    
    


    public Map<String, RemoteSourceItem> getRemoteSourceMap() {
		return remoteSourceMap;
	}

	public void setRemoteSourceMap(Map<String, RemoteSourceItem> remoteSourceMap) {
		this.remoteSourceMap = remoteSourceMap;
	}

	public Map<String, KeyMappingItem> getKeyMap() {
		return keyMap;
	}

	public void setKeyMap(Map<String, KeyMappingItem> keyMap) {
		this.keyMap = keyMap;
	}

    public String getRemoteSourceDefault() {
		return remoteSourceDefault;
	}

	public void setRemoteSourceDefault(String remoteSourceDefault) {
		this.remoteSourceDefault = remoteSourceDefault;
	}

	public String getLocalSourceDefault() {
		return localSourceDefault;
	}

	public void setLocalSourceDefault(String localSourceDefault) {
		this.localSourceDefault = localSourceDefault;
	}
}
