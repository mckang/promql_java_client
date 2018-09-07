package com.bdwise.prometheus.client.builder;

public enum QueryBuilderType {
	RangeQuery{

		@SuppressWarnings("unchecked")
		@Override
		public RangeQueryBuilder newInstance(String prometheusUrl) {
			return new RangeQueryBuilder(prometheusUrl);
		}
		
	},
	InstantQuery{

		@SuppressWarnings("unchecked")
		@Override
		public InstantQueryBuilder newInstance(String prometheusUrl) {
			return new InstantQueryBuilder(prometheusUrl);
		}
		
	},
	MetadaQuery{

		@SuppressWarnings("unchecked")
		@Override
		public QueryBuilder newInstance(String prometheusUrl) {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	TargetQuery{

		@SuppressWarnings("unchecked")
		@Override
		public QueryBuilder newInstance(String prometheusUrl) {
			// TODO Auto-generated method stub
			return null;
		}
		
	};
	
	public abstract <T extends QueryBuilder> T newInstance(String prometheusUrl);
}
