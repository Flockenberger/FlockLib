package at.flockenberger.flocklib.flockutil.data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import at.flockenberger.flocklib.flockgfx.FlockColor;
import at.flockenberger.flocklib.flocklog.FlockLogManager;
import at.flockenberger.flocklib.flockutil.ObjectUtils;

/**
 * <h1>DataContainer</h1><br>
 * A container is a collection of individual values. Each value has its own ID
 * and type. Container can also carry any number of child containers. <br>
 * Kepp in mind that there is no guarantee that a value is there in the
 * container. <br>
 * The {@link DataContainer} class does not permit any key values to be null.<br>
 * Data values may be null but might cause some unexpected behaviour!
 * 
 * @author Florian Wagner 
 *
 */
public class DataContainer implements Serializable {

	private static final long serialVersionUID = -3992739051020621845L;

	/**
	 * the underlying container map
	 */
	protected Map<Object, Object> container;

	/**
	 * Creates a new DataContainer instance
	 */
	public DataContainer() {
		container = new HashMap<Object, Object>();
	}

	/**
	 * Copy Constructor
	 * 
	 * @param bc the container to copy
	 */
	public DataContainer(DataContainer bc) {
		ObjectUtils.isNullThrow(bc, "DataContainer#DataContainer() : Parameter 'bc' must not be null!");
		this.container = new HashMap<Object, Object>(bc.container);
	}

	/**
	 * Stores all data inside the {@link DataContainer} <code>src</code> inside of
	 * this container. <br>
	 * <br>
	 * <b>Note: </b>If IDs collide while merging the data from this container will
	 * be overridden by the data of the <code>src</code> container!<br>
	 * 
	 * @param src the container to merge into this container
	 */
	public void merge(DataContainer src) {
		ObjectUtils.isNullThrow(src, "DataContainer#merge() : Parameter 'src' must not be null!");
		for (Object key : src.container.keySet())
			setData(key, src.getData(key));
	}

	/**
	 * Stores a given {@link Object} <code>object</code> with all its declared
	 * fields and current values inside this container as a sub-container.<br>
	 * 
	 * @param key    the key to store the data to
	 * @param object the object to store
	 * 
	 */
	public void setObject(Object key, Object object) {
		ObjectUtils.isNullThrow(key, "DataContainer#setObject() : Parameter 'key' must not be null!");
		Class<?> objClass = object.getClass();
		Field[] fields = objClass.getDeclaredFields();
		DataContainer subContainer = new DataContainer();
		for (Field field : fields) {
			subContainer.setField(field, object);
		}
		setContainer(key, subContainer);
	}

	/**
	 * Stores a given {@link DataContainer} <code>container</code> with all its
	 * entries as a sub-container.<br>
	 * 
	 * @param key       the key to store the data to
	 * @param container the container to store inside of this container
	 */
	public void setContainer(Object key, DataContainer container) {
		ObjectUtils.isNullThrow(key, "DataContainer#setContainer() : Parameter 'key' must not be null!");
		this.container.put(key, container);
	}

	/**
	 * Stores a {@link Field} object into this container.<br>
	 * The key parameter of this entry is the name as {@link String} of the given
	 * {@link Field}.<br>
	 * The given object <code>object</code> is the object where the given field is
	 * attached to.<br>
	 * The {@link Field} to be inserted even if it was declared private.<br>
	 * 
	 * @param field  the field object to store
	 * @param object the object where this field belongs to to get the value to
	 *               store
	 */
	public void setField(Field field, Object object) {
		ObjectUtils.isNullThrow(field, "DataContainer#setField() : Parameter 'field' must not be null!");
		ObjectUtils.isNullThrow(object, "DataContainer#setField() : Parameter 'object' must not be null!");

		String fieldName = field.getName();
		try {
			// force the field to be accessible even if it was declared private
			if (!field.isAccessible())
				field.setAccessible(true);
			Object value = field.get(object);
			container.put(fieldName, value);

		} catch (IllegalArgumentException | IllegalAccessException e) {
			container.put(fieldName, null);
		}

	}

	/**
	 * Stores the given (generic) data <code> value </code> to the given key
	 * <code> key </code>
	 * 
	 * @param key   the key to store the data to
	 * @param value the value
	 */
	public void setData(Object key, Object value) {
		ObjectUtils.isNullThrow(key, "DataContainer#setData() : Parameter 'key' must not be null!");
		container.put(key, value);
	}

	/**
	 * Stores a boolean parameter to the given key
	 * 
	 * @param key   the key to store the value to
	 * @param value the value to store
	 */
	public void setBool(Object key, boolean value) {
		ObjectUtils.isNullThrow(key, "DataContainer#setBool() : Parameter 'key' must not be null!");
		container.put(key, value);
	}

	/**
	 * Stores an int parameter to the given key
	 * 
	 * @param key   the key to store the value to
	 * @param value the value to store
	 */
	public void setInt(Object key, int value) {
		ObjectUtils.isNullThrow(key, "DataContainer#setInt() : Parameter 'key' must not be null!");
		container.put(key, value);
	}

	/**
	 * Stores a String parameter to the given key
	 * 
	 * @param key   the key to store the value to
	 * @param value the value to store
	 */
	public void setString(Object key, String value) {
		ObjectUtils.isNullThrow(key, "DataContainer#setString() : Parameter 'key' must not be null!");
		container.put(key, value);
	}

	/**
	 * Stores a long parameter to the given key
	 * 
	 * @param key   the key to store the value to
	 * @param value the value to store
	 */
	public void setLong(Object key, long value) {
		ObjectUtils.isNullThrow(key, "DataContainer#setLong() : Parameter 'key' must not be null!");
		container.put(key, value);
	}

	/**
	 * Stores a float parameter to the given key
	 * 
	 * @param key   the key to store the value to
	 * @param value the value to store
	 */
	public void setFloat(Object key, float value) {
		ObjectUtils.isNullThrow(key, "DataContainer#setFloat() : Parameter 'key' must not be null!");
		container.put(key, value);
	}

	/**
	 * Stores a double parameter to the given key
	 * 
	 * @param key   the key to store the value to
	 * @param value the value to store
	 */
	public void setDouble(Object key, double value) {
		ObjectUtils.isNullThrow(key, "DataContainer#setDouble() : Parameter 'key' must not be null!");
		container.put(key, value);
	}

	/**
	 * Stores a short parameter to the given key
	 * 
	 * @param key   the key to store the value to
	 * @param value the value to store
	 */
	public void setShort(Object key, short value) {
		ObjectUtils.isNullThrow(key, "DataContainer#setShort() : Parameter 'key' must not be null!");
		container.put(key, value);
	}

	/**
	 * Stores a byte parameter to the given key
	 * 
	 * @param key   the key to store the value to
	 * @param value the value to store
	 */
	public void setByte(Object key, byte value) {
		ObjectUtils.isNullThrow(key, "DataContainer#setByte() : Parameter 'key' must not be null!");
		container.put(key, value);
	}

	/**
	 * Stores a {@link FlockColor} parameter to the given key
	 * 
	 * @param key   the key to store the value to
	 * @param value the value to store
	 */
	public void setColor(Object key, FlockColor value) {
		ObjectUtils.isNullThrow(key, "DataContainer#setColor() : Parameter 'key' must not be null!");
		container.put(key, value);
	}

	/**
	 * Returns a boolean parameter of the given key.<br>
	 * This method returns always false if the value was not found!
	 * 
	 * @param key the key to get the value from
	 * @return the stored value or null if it was not found or not this datatype!
	 */
	public boolean getBool(Object key) {
		ObjectUtils.isNullThrow(key, "DataContainer#getBool() : Parameter 'key' must not be null!");
		boolean ret = false;
		try {
			ret = (boolean) container.get(key);
			return ret;
		} catch (Exception e) {
			FlockLogManager.getGlobalLogger().error("DataContainer#getBool() : Boolean value was not found!");
			return false;
		}
	}

	/**
	 * Returns an int parameter of the given key.<br>
	 * This method returns always -1 if the value was not found!
	 * 
	 * @param key the key to get the value from
	 * @return the stored value or null if it was not found or not this datatype!
	 */
	public int getInt(Object key) {
		ObjectUtils.isNullThrow(key, "DataContainer#getInt() : Parameter 'key' must not be null!");
		int ret = -1;
		try {
			ret = (int) container.get(key);
			return ret;
		} catch (Exception e) {
			FlockLogManager.getGlobalLogger().error("DataContainer#getInt() : Integer value was not found!");
			return -1;
		}
	}

	/**
	 * Returns a {@link String} parameter of the given key.<br>
	 * This method returns always an empty {@link String} if the value was not
	 * found!
	 * 
	 * @param key the key to get the value from
	 * @return the stored value or null if it was not found or not this datatype!
	 */
	public String getString(Object key) {
		ObjectUtils.isNullThrow(key, "DataContainer#getString() : Parameter 'key' must not be null!");
		String ret;
		try {
			ret = (String) container.get(key);
			return ret;
		} catch (Exception e) {
			FlockLogManager.getGlobalLogger().error("DataContainer#getString() : String value was not found!");
			return "";
		}
	}

	/**
	 * Returns a long parameter of the given key.<br>
	 * This method returns always -1 if the value was not found!
	 * 
	 * @param key the key to get the value from
	 * @return the stored value or null if it was not found or not this datatype!
	 */
	public long getLong(Object key) {
		ObjectUtils.isNullThrow(key, "DataContainer#getLong() : Parameter 'key' must not be null!");
		long ret = -1;
		try {
			ret = (long) container.get(key);
			return ret;
		} catch (Exception e) {
			FlockLogManager.getGlobalLogger().error("DataContainer#getLong() : Long value was not found!");

			return -1;
		}
	}

	/**
	 * Returns a float parameter of the given key.<br>
	 * This method returns always -1 if the value was not found!
	 * 
	 * @param key the key to get the value from
	 * @return the stored value or null if it was not found or not this datatype!
	 */
	public float getFloat(Object key) {
		ObjectUtils.isNullThrow(key, "DataContainer#getFloat() : Parameter 'key' must not be null!");
		float ret = -1;
		try {
			ret = (float) container.get(key);
			return ret;
		} catch (Exception e) {
			FlockLogManager.getGlobalLogger().error("DataContainer#getFloat() : Float value was not found!");
			return -1f;
		}
	}

	/**
	 * Returns a double parameter of the given key.<br>
	 * This method returns always -1 if the value was not found!
	 * 
	 * @param key the key to get the value from
	 * @return the stored value or null if it was not found or not this datatype!
	 */
	public double getDouble(Object key) {
		ObjectUtils.isNullThrow(key, "DataContainer#getDouble() : Parameter 'key' must not be null!");
		double ret = -1;
		try {
			ret = (double) container.get(key);
			return ret;
		} catch (Exception e) {
			FlockLogManager.getGlobalLogger().error("DataContainer#getDouble() : Double value was not found!");
			return -1d;
		}
	}

	/**
	 * Returns a short parameter of the given key.<br>
	 * This method returns always -1 if the value was not found!
	 * 
	 * @param key the key to get the value from
	 * @return the stored value or null if it was not found or not this datatype!
	 */
	public short getShort(Object key) {
		ObjectUtils.isNullThrow(key, "DataContainer#getShort() : Parameter 'key' must not be null!");
		short ret = -1;
		try {
			ret = (short) container.get(key);
			return ret;
		} catch (Exception e) {
			FlockLogManager.getGlobalLogger().error("DataContainer#getShort() : Short value was not found!");
			return -1;
		}
	}

	/**
	 * Returns a byte parameter of the given key.<br>
	 * This method returns always -1 if the value was not found!
	 * 
	 * @param key the key to get the value from
	 * @return the stored value or null if it was not found or not this datatype!
	 */
	public byte getByte(Object key) {
		ObjectUtils.isNullThrow(key, "DataContainer#getByte() : Parameter 'key' must not be null!");
		byte ret = -1;
		try {
			ret = (byte) container.get(key);
			return ret;
		} catch (Exception e) {
			FlockLogManager.getGlobalLogger().error("DataContainer#getByte() : Byte value was not found!");
			return -1;
		}
	}

	/**
	 * Returns a {@link FlockColor} parameter of the given key.<br>
	 * This method returns always -1 if the value was not found!
	 * 
	 * @param key the key to get the value from
	 * @return the stored value or null if it was not found or not this datatype!
	 */
	public FlockColor getColor(Object key) {
		ObjectUtils.isNullThrow(key, "DataContainer#getColor() : Parameter 'key' must not be null!");
		FlockColor ret = new FlockColor();
		try {
			ret = (FlockColor) container.get(key);
			return ret;
		} catch (Exception e) {
			FlockLogManager.getGlobalLogger().error("DataContainer#getColor() : Color value was not found!");
			return ret;
		}
	}

	/**
	 * Gets the data with the given key.<br>
	 * This method is unchecked the user has to make sure that the retrieved value
	 * is correct.
	 * 
	 * @param     <T> the type of the returned data as generic data type.
	 * @param key the key to get the value from
	 */
	@SuppressWarnings("unchecked")
	public <T> T getData(Object key) {
		ObjectUtils.isNullThrow(key, "DataContainer#getData() : Parameter 'key' must not be null!");
		return (T) container.get(key);
	}

	/**
	 * Returns a <b>copy </b> {@link DataContainer} parameter of the given key.<br>
	 * This method returns always null if the value was not found!
	 * 
	 * @param key the key to get the value from
	 * @return the stored value or null if it was not found or not this datatype!
	 */
	public DataContainer getContainer(Object key) {
		ObjectUtils.isNullThrow(key, "DataContainer#getContainer() : Parameter 'key' must not be null!");
		DataContainer subContainer = null;

		try {
			subContainer = new DataContainer((DataContainer) container.get(key));
			return subContainer;

		} catch (Exception e) {
			FlockLogManager.getGlobalLogger().error("DataContainer#getContainer() : Container value was not found!");
			return subContainer;
		}
	}

	/**
	 * Returns the sotred {@link DataContainer} of the given key.<br>
	 * This method returns always null if the value was not found!
	 * 
	 * @param key the key to get the value from
	 * @return the stored value or null if it was not found or not this datatype!
	 */
	public DataContainer getContainerInstance(Object key) {
		ObjectUtils.isNullThrow(key, "DataContainer#getContainerInstance() : Parameter 'key' must not be null!");
		try {
			return (DataContainer) container.get(key);
		} catch (Exception e) {
			FlockLogManager.getGlobalLogger().error("DataContainer#getContainer() : Container value was not found!");
			return null;
		}
	}

	/**
	 * Returns an {@link Object} of type <code>T</code> with the class given by
	 * <code>clazz</code>.<br>
	 * The object is created from a sub-container inside of this container with the
	 * given id <code>key</code>.<br>
	 * For this method to work the class to be instantiated must have a default
	 * constructor, otherwise null is returned.<br>
	 * 
	 * <br>
	 * This method, in any case, tries to create an {@link Object} of the given
	 * {@link Class} <code>clazz</code>. If that creation fails, null will be
	 * returned.<br>
	 * If this created object does not match the stored object, it will be returned
	 * as a newly created object with nothing changed.<br>
	 * Otherwise all {@link Field} values of the given object will be overridden
	 * with the values stored inside of the sub-container.
	 * 
	 * @param       <T> the type of the object to be returned
	 * @param key   the key of the object to get
	 * @param clazz the class of the object
	 * @return an object of the type <code>clazz</code> with (or without) the stored
	 *         values or null if it could not be created
	 */
	public <T> T getObject(Object key, Class<T> clazz) {
		ObjectUtils.isNullThrow(key, "DataContainer#getObject() : Parameter 'key' must not be null!");
		ObjectUtils.isNullThrow(clazz, "DataContainer#getObject() : Parameter 'clazz' must not be null!");
		DataContainer container = getContainer(key);
		return container.toObject(clazz);
	}

	/**
	 * Returns an {@link Object} of type <code>T</code> with the class given by
	 * <code>clazz</code>.<br>
	 * The object is created from this container.<br>
	 * For this method to work the class to be instantiated must have a default
	 * constructor, otherwise null is returned.<br>
	 * 
	 * <br>
	 * This method, in any case, tries to create an {@link Object} of the given
	 * {@link Class} <code>clazz</code>. If that creation fails, null will be
	 * returned.<br>
	 * If this created object does not match the stored object, it will be returned
	 * as a newly created object with nothing changed.<br>
	 * Otherwise all {@link Field} values of the given object will be overridden
	 * with the values stored inside of the sub-container.
	 * 
	 * @param       <T> the type of the object to be returned
	 * @param clazz the class of the object
	 * @return an object of the type <code>clazz</code> with (or without) the stored
	 *         values or null if it could not be created
	 */
	@SuppressWarnings("unchecked")
	public <T> T toObject(Class<T> clazz) {
		ObjectUtils.isNullThrow(clazz, "DataContainer#toObject() : Parameter 'clazz' must not be null!");
		Object returnObj = null;
		try {
			returnObj = clazz.newInstance();
			if (container != null) {
				Field[] fields = returnObj.getClass().getDeclaredFields();

				for (Field field : fields) {
					Object value = getData(field.getName());
					if (value != null) {
						if (!field.isAccessible())
							field.setAccessible(true);
						field.set(returnObj, value);
					}
				}
			}
			return (T) returnObj;
		} catch (InstantiationException | IllegalAccessException e) {
			FlockLogManager.getGlobalLogger().error(
					"DataContainer#getObject() : Object value was not found! Make sure the Object to load has a default consturctor!");
		}
		return null;
	}

	/**
	 * Removes the given key from this container.<br>
	 * 
	 * @param key the key of the object to remove
	 */
	public void remove(Object key) {
		ObjectUtils.isNullThrow(key, "DataContainer#remove() : Parameter 'key' must not be null!");
		container.remove(key);
	}

	/**
	 * Copies this container into the {@link DataContainer} <code>dst</code>.<br>
	 * Any value that is present in the destination container where the IDs collide
	 * will be overridden by the value of this container.<br>
	 * 
	 * @param dst the container to copy this container's data into
	 */
	public void copyTo(DataContainer dst) {
		ObjectUtils.isNullThrow(dst, "DataContainer#copyTo() : Parameter 'dst' must not be null!");
		for (Object key : container.keySet()) {
			dst.setData(key, container.get(key));
		}
	}

	public Class<?> getType(Object key) {
		ObjectUtils.isNullThrow(key, "DataContainer#getType() : Parameter 'key' must not be null!");
		return container.get(key).getClass();
	}

	/**
	 * Clears the whole data container.
	 */
	public void clear() {
		container.clear();
	}

	/**
	 * Prints this container into the console.<br>
	 */
	public void print() {
		_print(this, "");
	}

	private void _print(DataContainer container, String indend) {

		Set<Object> keys = container.container.keySet();
		for (Object key : keys) {
			Object data = container.getData(key);

			if (data instanceof DataContainer) {
				System.out.println(indend + "-Key: " + key.toString() + ", Data: DataContainer with content:");
				_print((DataContainer) data, indend + "	");
			} else
				System.out.println(indend + "-Key: " + key + ", Data: " + data);
		}
	}
}
