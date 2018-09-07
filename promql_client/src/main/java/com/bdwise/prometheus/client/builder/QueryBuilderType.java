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
	SeriesMetadaQuery{

		@SuppressWarnings("unchecked")
		@Override
		public QueryBuilder newInstance(String prometheusUrl) {
			return new SeriesMetaQueryBuilder(prometheusUrl);
		}
		
	},
	LabelMetadaQuery{

		@SuppressWarnings("unchecked")
		@Override
		public QueryBuilder newInstance(String prometheusUrl) {
			return new LabelMetaQueryBuilder(prometheusUrl);
		}
		
	},

	TargetMetadaQuery{

		@SuppressWarnings("unchecked")
		@Override
		public QueryBuilder newInstance(String prometheusUrl) {
			return new TargetMetaQueryBuilder(prometheusUrl);
		}
		
	},

	AlertManagerMetadaQuery{

		@SuppressWarnings("unchecked")
		@Override
		public QueryBuilder newInstance(String prometheusUrl) {
			return new AlertManagerMetaQueryBuilder(prometheusUrl);
		}
		
	},
	
	StatusMetadaQuery{

		@SuppressWarnings("unchecked")
		@Override
		public QueryBuilder newInstance(String prometheusUrl) {
			return new StatusMetaQueryBuilder(prometheusUrl);
		}
		
	};

	
	public abstract <T extends QueryBuilder> T newInstance(String prometheusUrl);
}
