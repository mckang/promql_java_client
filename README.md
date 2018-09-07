# promql_java_client
# Usage
## Make API URL
### RangeQuery
```
RangeQueryBuilder rangeQueryBuilder =  QueryBuilderType.RangeQuery.newInstance(TARGET_SERVER);
URI targetUri = rangeQueryBuilder.withQuery("irate(received_api_call_total[60s])")
		                 .withStartEpochTime(System.currentTimeMillis() / 1000 - 60*10)
		                 .withEndEpochTime(System.currentTimeMillis() / 1000)
		                 .withStepTime("60s")
		                 .build();
``` 
### InstantQuery
```
	InstantQueryBuilder iqb = QueryBuilderType.InstantQuery.newInstance(TARGET_SERVER);
	URI targetUri = iqb.withQuery("irate(received_api_call_total[60s])").build();
``` 
### LabelQuery
```
	LabelMetaQueryBuilder lmqb = QueryBuilderType.LabelMetadaQuery.newInstance(TARGET_SERVER);
	URI targetUri = lmqb.withLabel("pod").build();
``` 
### Statuc(Config)Query
```
	StatusMetaQueryBuilder smqb = QueryBuilderType.StatusMetadaQuery.newInstance(TARGET_SERVER);
	URI targetUri = smqb.build();
``` 
### TargetQuery
```
	TargetMetaQueryBuilder tmqb = QueryBuilderType.TargetMetadaQuery.newInstance(TARGET_SERVER);
	URI targetUri = tmqb.build();
``` 
### AlertManagerQuery
```
	AlertManagerMetaQueryBuilder ammqb = QueryBuilderType.AlertManagerMetadaQuery.newInstance(TARGET_SERVER);
	URI targetUri = ammqb.build();
``` 
                     
