package net.alxb.cloner;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * Test class to test-drive {@link DeepCloneable} class implementation
 * 
 * @author Alex Borisov
 * 
 */
public class DeepCloneableShould {

	private int intValue;
	private Date date;
	private DeepCloneable cloneable;
	private DeepCloneable cloned;
	private List<Date> list;

	@Test
	public void notThrowCloneNotSupportedException()
			throws CloneNotSupportedException {
		doClone();
	}

	@Test
	public void createNewButEqualObject() throws CloneNotSupportedException {
		doClone();
		assertThat(cloned).isNotNull().isNotSameAs(cloneable)
				.isEqualTo(cloneable);
	}

	@Test
	public void copyPrimitiveFiled() throws CloneNotSupportedException {
		intValue = 10;
		doClone();
		assertThat(cloned.getInt()).isEqualTo(intValue);
	}

	@Test
	public void createEqualObjectField() throws CloneNotSupportedException {
		date = new Date();
		doClone();
		assertThat(cloned.getDate()).isEqualTo(cloneable.getDate());
	}

	@Test
	public void createNewObjectField() throws CloneNotSupportedException {
		date = new Date();
		doClone();
		assertThat(cloned.getDate()).isNotSameAs(cloneable.getDate());
	}

	@Test
	public void createEqualCollectionField() throws CloneNotSupportedException {
		list = listOfDatesWithDaysOfMonth(1, 15, 25, 26);
		doClone();
		assertThat(cloned.getList()).isEqualTo(cloneable.getList());
	}

	@Test
	public void createNewCollectionField() throws CloneNotSupportedException {
		list = listOfDatesWithDaysOfMonth(2, 5);
		doClone();
		assertThat(cloned.getList()).isNotSameAs(cloneable.getList());
	}

	@Test
	public void createEqualElemetsOfCollectionField() throws CloneNotSupportedException {
		list = listOfDatesWithDaysOfMonth(8);
		doClone();
		
		int firstElementIndex = 0;
		Date firstClonedDate = cloned.getList().get(firstElementIndex);
		Date firstCloneableDate = cloneable.getList().get(firstElementIndex);
		
		assertThat(firstClonedDate).isEqualTo(firstCloneableDate);
	}
	
	@Test
	public void createNewElementsOfCollectionField() throws CloneNotSupportedException {
		list = listOfDatesWithDaysOfMonth(9, 10);
		doClone();
		
		int secondElementIndex = 1;
		Date secondClonedDate = cloned.getList().get(secondElementIndex);
		Date secondCloneableDate = cloneable.getList().get(secondElementIndex);
		
		assertThat(secondClonedDate).isNotSameAs(secondCloneableDate);
	}

	
	private void doClone() throws CloneNotSupportedException {
		cloneable = new DeepCloneable(intValue, date, list);
		cloned = cloneable.clone();
	}

	private List<Date> listOfDatesWithDaysOfMonth(Integer... daysOfMonth) {
		List<Date> dates = Lists.transform(Arrays.asList(daysOfMonth),
				new Function<Integer, Date>() {
					public Date apply(Integer dayOfMonth) {
						return dateWithDayOfMonth(dayOfMonth);
					}
				});
		return Lists.newArrayList(dates);
	}

	private Date dateWithDayOfMonth(int dayOfMonth) {
		return new DateTime().withDayOfMonth(dayOfMonth).toDate();
	}

}