package net.alxb.cloner;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.rits.cloning.Cloner;

/**
 * Deep cloneable object
 * 
 * @author Alex Borisov
 * 
 */
public class DeepCloneable implements Cloneable {

	private final int intValue;
	private final Date date;
	private final List<Date> list;

	DeepCloneable(int intValue, Date date, List<Date> list) {
		this.intValue = intValue;
		this.date = date;
		this.list = list;
	}

	public int getInt() {
		return intValue;
	}

	public Date getDate() {
		return date;
	}

	public List<Date> getList() {
		return list;
	}

	@Override
	public DeepCloneable clone() throws CloneNotSupportedException {
		Cloner cloner = new Cloner();
		DeepCloneable clone = cloner.deepClone(this);
		return clone;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DeepCloneable)) {
			return false;
		}

		DeepCloneable other = (DeepCloneable) obj;
		return new EqualsBuilder().append(intValue, other.intValue)
				.append(date, other.date).append(list, other.list).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(intValue).append(date).append(list)
				.toHashCode();
	}

}