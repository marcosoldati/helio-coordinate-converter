package ch.fhnw.i4ds.helio.coordinate.converter.option;

import java.util.Set;
import java.util.TreeSet;

import ch.fhnw.i4ds.helio.coordinate.util.Unit;

/**
 * Descriptor for any converter options. This object does not store any values, rather
 * it acts as a key of a Map that is passed to the algorithms. 
 * Property 'name' must be unique.
 * @author marco soldati at fhwn ch.
 *
 * @param <T>
 */
public final class ConverterOption<T> implements Comparable<ConverterOption<T>> {
	/**
	 * Make sure the names are unique
	 */
	private final static Set<String> nameSet = new TreeSet<String>();
	
	private final T value;
	private final Unit unit;
	private final String name;
	private final String description;
	
	public ConverterOption(String name, String description, T value, Unit unit) {
		this.name = name;
		this.description = description;
		this.value = value;
		this.unit = unit;
		assertNameIsUnique(name);
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	/**
	 * Get the default value for this option.
	 * @return the actual value.
	 */
	public T getValue() {
		return value;
	}
	
	/**
	 * Get the unit for the value.
	 * @return the value's unit.
	 */
	public Unit getUnit() {
		return unit;
	}
	
	@Override
	public String toString() {
		return value.toString() + unit.getSymbol();
	}

	@Override
	public int compareTo(ConverterOption<T> o) {
		return o.getName().compareTo(getName());
	}
	
	@Override
	public int hashCode() {
		return 13 * name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ConverterOption) {
			@SuppressWarnings("unchecked")
			ConverterOption<T> converterOption = (ConverterOption<T>) obj;
			return converterOption.name.equals(this.name);
		}
		return false;
	}
	
	private void assertNameIsUnique(String name) {
		if (nameSet.contains(name)) {
			throw new IllegalArgumentException("ConverterOption already contains an object with name '" + name + "'");
		}
		nameSet.add(name);
	}
}
