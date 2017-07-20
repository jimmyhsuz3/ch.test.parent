package ch.test.cxf;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ProfileViewQuery {
	private List<PidCondition> and = new java.util.ArrayList<PidCondition>();
	@JsonProperty("$and")
	public List<PidCondition> getAnd() {
		return and;
	}
	public class PidCondition {
		private Condition pid;
		private PidCondition(Integer $gt, Integer $lt) {
			this.pid = new Condition($gt, $lt);
		}
		public Condition getPid() {
			return pid;
		}
		@JsonInclude(Include.NON_NULL)
		public class Condition {
			private Integer $gt;
			private Integer $lt;
			private Condition(Integer $gt, Integer $lt) {
				this.$gt = $gt;
				this.$lt = $lt;
			}
			public Integer get$gt() {
				return $gt;
			}
			public Integer get$lt() {
				return $lt;
			}
		}
	}
	public void setPidLessThan(int lessThan){
		and.add(new PidCondition(null, lessThan));
	}
	public void setPidGreaterThan(int greaterThan){
		and.add(new PidCondition(greaterThan, null));
	}
}