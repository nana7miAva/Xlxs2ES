package cache;

import entity.CaseDetailInfo;
import entity.CaseInfo;

import java.util.concurrent.ConcurrentHashMap;

public class MemoryCache {

	//方案配置缓存 <caseId, caseDetailInfo>
	public static ConcurrentHashMap<String, CaseDetailInfo> schemeCache = new ConcurrentHashMap<>();

	//用于失效方案比对缓存 <方案id，>
	public static ConcurrentHashMap<String, CaseInfo> schemeCacheTaskInfo = new ConcurrentHashMap<>();

}
