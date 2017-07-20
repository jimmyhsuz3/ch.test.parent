package ch.test.cxf;
import com.fasterxml.jackson.annotation.JsonProperty;
public class MongoDBJSON {
	private MongoDB mongoDB;
	public MongoDBJSON(String dbName, String dbCollectionName, String methodName, Object queryObj) {
		this.mongoDB = new MongoDB(dbName, dbCollectionName, methodName, queryObj);
	}
	@JsonProperty("MongoDB")
	public MongoDB getMongoDB() {
		return mongoDB;
	}
	public class MongoDB {
		private DB db;
		private DBCollection dbCollection;
		private MongoDB(String dbName, String dbCollectionName, String methodName, Object queryObj) {
			this.db = new DB(dbName);
			this.dbCollection = new DBCollection(dbCollectionName, methodName, queryObj);
		}
		@JsonProperty("DB")
		public DB getDb() {
			return db;
		}
		@JsonProperty("DBCollection")
		public DBCollection getDbCollection() {
			return dbCollection;
		}
		public class DB {
			private String name;
			private DB(String name) {
				this.name = name;
			}
			@JsonProperty("Name")
			public String getName() {
				return name;
			}
		}
		public class DBCollection {
			private String name;
			private Method method;
			private DBCollection(String name, String methodName, Object queryObj) {
				this.name = name;
				this.method = new Method(methodName, queryObj);
			}
			@JsonProperty("Name")
			public String getName() {
				return name;
			}
			@JsonProperty("Method")
			public Method getMethod() {
				return method;
			}
			public class Method {
				private String name;
				private Object queryObj;
				private Method(String name, Object queryObj) {
					this.name = name;
					this.queryObj = queryObj;
				}
				@JsonProperty("Name")
				public String getName() {
					return name;
				}
				@JsonProperty("QueryObj")
				public Object getQueryObj() {
					return queryObj;
				}
			}
		}
	}
}