package com.imarchenko.ua.prometheus.entities;

import java.util.Map;

public class PrometheusDataEtity {
	private String status;
	private Data data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {
		private Result[] result;
		private String resultType;

		public Result[] getResult() {
			return result;
		}

		public void setResult(Result[] result) {
			this.result = result;
		}

		public String getResultType() {
			return resultType;
		}

		public void setResultType(String resultType) {
			this.resultType = resultType;
		}

		public class Result {
			private Map<String, String> metric;
			private String [][] values;

			public Map<String, String> getMetric() {
				return metric;
			}

			public void setMetric(Map<String, String> metric) {
				this.metric = metric;
			}

			public String [][] getValues() {
				return values;
			}

			public void setValues(String[][] values) {
				this.values = values;
			}

		}
	}
}
