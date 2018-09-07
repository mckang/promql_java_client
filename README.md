# Prometheus Query API Java Client Usage
***
## Build API URL
***
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
***
## Parse Result
***
### Parse Matrix Type Query Result
```
       DefaultQueryResult<MatrixData> result = ConvertUtil.convertQueryResultString(rtVal);
```
### Parse Vector Type Query Result
```
       DefaultQueryResult<VectorData> result = ConvertUtil.convertQueryResultString(rtVal);
```
### Parse Scalar Type Query Result
```
       DefaultQueryResult<ScalaData> result = ConvertUtil.convertQueryResultString(rtVal);
```
### Parse Label API Result
```
       DefaultLabelResult result = ConvertUtil.convertLabelResultString(rtVal);
```
### Parse Status(Config) API Result
```
       DefaultConfigResult result = ConvertUtil.convertConfigResultString(rtVal);
```
### Parse Targets API Result
```
       DefaultTargetResult result = ConvertUtil.convertTargetResultString(rtVal);
```
### Parse AlertManager API Result
```
       DefaultAlertManagerResult result = ConvertUtil.convertAlertManagerResultString(rtVal);
```
                     
