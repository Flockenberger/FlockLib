package at.flockenberger.flocklib.flockutil;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

/**
 * Buffer utilities.
 * 
 * @author Florian Wagner
 *
 */
public final class BufferUtils
{
	/**
	 * Allocates a direct native-ordered {@code ByteBuffer} with the specified
	 * capacity.
	 *
	 * @param capacity the capacity, in bytes
	 *
	 * @return a {@code ByteBuffer}
	 */
	public static ByteBuffer createByteBuffer(int capacity)
	{
		return ByteBuffer.allocateDirect(capacity).order(ByteOrder.nativeOrder());
	}

	/**
	 * Allocates a direct native-order {@code ShortBuffer} with the specified number
	 * of elements.
	 *
	 * @param capacity the capacity, in shorts
	 *
	 * @return a {@code ShortBuffer}
	 */
	public static ShortBuffer createShortBuffer(int capacity)
	{
		return createByteBuffer(getAllocationSize(capacity, 1)).asShortBuffer();
	}

	/**
	 * Allocates a direct native-order {@code CharBuffer} with the specified number
	 * of elements.
	 *
	 * @param capacity the capacity, in chars
	 *
	 * @return a {@code CharBuffer}
	 */
	public static CharBuffer createCharBuffer(int capacity)
	{
		return createByteBuffer(getAllocationSize(capacity, 1)).asCharBuffer();
	}

	/**
	 * Allocates a direct native-order {@code IntBuffer} with the specified number
	 * of elements.
	 *
	 * @param capacity the capacity, in ints
	 *
	 * @return an {@code IntBuffer}
	 */
	public static IntBuffer createIntBuffer(int capacity)
	{
		return createByteBuffer(getAllocationSize(capacity, 2)).asIntBuffer();
	}

	/**
	 * Allocates a direct native-order {@code LongBuffer} with the specified number
	 * of elements.
	 *
	 * @param capacity the capacity, in longs
	 *
	 * @return a {@code LongBuffer}
	 */
	public static LongBuffer createLongBuffer(int capacity)
	{
		return createByteBuffer(getAllocationSize(capacity, 3)).asLongBuffer();
	}

	/**
	 * Allocates a direct native-order {@code FloatBuffer} with the specified number
	 * of elements.
	 *
	 * @param capacity the capacity, in floats
	 *
	 * @return a FloatBuffer
	 */
	public static FloatBuffer createFloatBuffer(int capacity)
	{
		return createByteBuffer(getAllocationSize(capacity, 2)).asFloatBuffer();
	}

	/**
	 * Allocates a direct native-order {@code DoubleBuffer} with the specified
	 * number of elements.
	 *
	 * @param capacity the capacity, in doubles
	 *
	 * @return a {@code DoubleBuffer}
	 */
	public static DoubleBuffer createDoubleBuffer(int capacity)
	{
		return createByteBuffer(getAllocationSize(capacity, 3)).asDoubleBuffer();
	}

	/**
	 * Allocates a {@link ByteBuffer} from the given array.<br>
	 * If the array is pointing to a null reference this method will throw a
	 * {@link NullPointerException}.
	 * 
	 * @param array the array to put into a {@link ByteBuffer}
	 * @return a new {@link ByteBuffer}
	 */
	public static ByteBuffer fromArray(byte[] array)
	{
		ObjectUtils.isNullThrow(array);
		ByteBuffer buffer = createByteBuffer(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}

	/**
	 * Allocates a {@link ShortBuffer} from the given array.<br>
	 * If the array is pointing to a null reference this method will throw a
	 * {@link NullPointerException}.
	 * 
	 * @param array the array to put into a {@link ShortBuffer}
	 * @return a new {@link ShortBuffer}
	 */
	public static ShortBuffer fromArray(short[] array)
	{
		ObjectUtils.isNullThrow(array);
		ShortBuffer buffer = createShortBuffer(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}

	/**
	 * Allocates a {@link CharBuffer} from the given array.<br>
	 * If the array is pointing to a null reference this method will throw a
	 * {@link NullPointerException}.
	 * 
	 * @param array the array to put into a {@link CharBuffer}
	 * @return a new {@link CharBuffer}
	 */
	public static CharBuffer fromArray(char[] array)
	{
		ObjectUtils.isNullThrow(array);
		CharBuffer buffer = createCharBuffer(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}

	/**
	 * Allocates a {@link IntBuffer} from the given array.<br>
	 * If the array is pointing to a null reference this method will throw a
	 * {@link NullPointerException}.
	 * 
	 * @param array the array to put into a {@link IntBuffer}
	 * @return a new {@link IntBuffer}
	 */
	public static IntBuffer fromArray(int[] array)
	{
		ObjectUtils.isNullThrow(array);
		IntBuffer buffer = createIntBuffer(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}

	/**
	 * Allocates a {@link LongBuffer} from the given array.<br>
	 * If the array is pointing to a null reference this method will throw a
	 * {@link NullPointerException}.
	 * 
	 * @param array the array to put into a {@link LongBuffer}
	 * @return a new {@link LongBuffer}
	 */
	public static LongBuffer fromArray(long[] array)
	{
		ObjectUtils.isNullThrow(array);
		LongBuffer buffer = createLongBuffer(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}

	/**
	 * Allocates a {@link DoubleBuffer} from the given array.<br>
	 * If the array is pointing to a null reference this method will throw a
	 * {@link NullPointerException}.
	 * 
	 * @param array the array to put into a {@link DoubleBuffer}
	 * @return a new {@link DoubleBuffer}
	 */
	public static DoubleBuffer fromArray(double[] array)
	{
		ObjectUtils.isNullThrow(array);
		DoubleBuffer buffer = createDoubleBuffer(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}

	/**
	 * Allocates a {@link FloatBuffer} from the given array.<br>
	 * If the array is pointing to a null reference this method will throw a
	 * {@link NullPointerException}.
	 * 
	 * @param array the array to put into a {@link FloatBuffer}
	 * @return a new {@link FloatBuffer}
	 */
	public static FloatBuffer fromArray(float[] array)
	{
		ObjectUtils.isNullThrow(array);
		FloatBuffer buffer = createFloatBuffer(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}

	/**
	 * Resizes the given {@link ByteBuffer} to the new capacity.<br>
	 * 
	 * @param buffer      the buffer to resize
	 * @param newCapacity the new capacity of the buffer
	 * @return the resized {@link ByteBuffer}
	 */
	public static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity)
	{
		ObjectUtils.isNullThrow(buffer);

		if (newCapacity < buffer.capacity())
			throw new IllegalArgumentException("The new capacity must be greater than the current capacity");
		ByteBuffer newBuffer = createByteBuffer(newCapacity);
		buffer.flip();
		newBuffer.put(buffer);
		return newBuffer;
	}

	/**
	 * Clones the given Buffer into a new Buffer.<br>
	 * The original Buffer <code>original</code> is preserved.
	 * 
	 * @param <T>      the {@link Buffer} type to clone.<br>
	 * @param original the original buffer to clone
	 * @return the cloned buffer or null if the type was not supported!
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Buffer> T clone(T original)
	{
		ObjectUtils.isNullThrow(original);

		if (original instanceof ByteBuffer)
		{
			return (T) cloneByteBuffer((ByteBuffer) original);
		} else if (original instanceof FloatBuffer)
		{
			return (T) cloneFloatBuffer((FloatBuffer) original);
		} else if (original instanceof ShortBuffer)
		{
			return (T) cloneShortBuffer((ShortBuffer) original);
		} else if (original instanceof CharBuffer)
		{
			return (T) cloneCharBuffer((CharBuffer) original);
		} else if (original instanceof IntBuffer)
		{
			return (T) cloneIntBuffer((IntBuffer) original);
		} else if (original instanceof LongBuffer)
		{
			return (T) cloneLongBuffer((LongBuffer) original);
		} else if (original instanceof DoubleBuffer)
		{ return (T) cloneDoubleBuffer((DoubleBuffer) original); }

		return null;
	}

	/**
	 * Clones the given {@link ByteBuffer} into a new {@link ByteBuffer}. <br>
	 * The original buffer is preserved.
	 * 
	 * @param original the {@link ByteBuffer} to copy
	 * @return a clone of the given buffer
	 */
	public static ByteBuffer cloneByteBuffer(ByteBuffer original)
	{
		ObjectUtils.isNullThrow(original);
		ByteBuffer clone = createByteBuffer(original.capacity());
		original.rewind();
		clone.put(original);
		original.rewind();
		clone.flip();
		return clone;
	}

	/**
	 * Clones the given {@link CharBuffer} into a new {@link CharBuffer}. <br>
	 * The original buffer is preserved.
	 * 
	 * @param original the {@link CharBuffer} to copy
	 * @return a clone of the given buffer
	 */
	public static CharBuffer cloneCharBuffer(CharBuffer original)
	{
		ObjectUtils.isNullThrow(original);
		CharBuffer clone = createCharBuffer(original.capacity());
		original.rewind();
		clone.put(original);
		original.rewind();
		clone.flip();
		return clone;
	}

	/**
	 * Clones the given {@link ShortBuffer} into a new {@link ShortBuffer}. <br>
	 * The original buffer is preserved.
	 * 
	 * @param original the {@link ShortBuffer} to copy
	 * @return a clone of the given buffer
	 */
	public static ShortBuffer cloneShortBuffer(ShortBuffer original)
	{
		ObjectUtils.isNullThrow(original);
		ShortBuffer clone = createShortBuffer(original.capacity());
		original.rewind();
		clone.put(original);
		original.rewind();
		clone.flip();
		return clone;
	}

	/**
	 * Clones the given {@link FloatBuffer} into a new {@link FloatBuffer}. <br>
	 * The original buffer is preserved.
	 * 
	 * @param original the {@link FloatBuffer} to copy
	 * @return a clone of the given buffer
	 */
	public static FloatBuffer cloneFloatBuffer(FloatBuffer original)
	{
		ObjectUtils.isNullThrow(original);
		FloatBuffer clone = createFloatBuffer(original.capacity());
		original.rewind();
		clone.put(original);
		original.rewind();
		clone.flip();
		return clone;
	}

	/**
	 * Clones the given {@link DoubleBuffer} into a new {@link DoubleBuffer}. <br>
	 * The original buffer is preserved.
	 * 
	 * @param original the {@link DoubleBuffer} to copy
	 * @return a clone of the given buffer
	 */
	public static DoubleBuffer cloneDoubleBuffer(DoubleBuffer original)
	{
		ObjectUtils.isNullThrow(original);
		DoubleBuffer clone = createDoubleBuffer(original.capacity());
		original.rewind();
		clone.put(original);
		original.rewind();
		clone.flip();
		return clone;
	}

	/**
	 * Clones the given {@link IntBuffer} into a new {@link IntBuffer}. <br>
	 * The original buffer is preserved.
	 * 
	 * @param original the {@link IntBuffer} to copy
	 * @return a clone of the given buffer
	 */
	public static IntBuffer cloneIntBuffer(IntBuffer original)
	{
		ObjectUtils.isNullThrow(original);
		IntBuffer clone = createIntBuffer(original.capacity());
		original.rewind();
		clone.put(original);
		original.rewind();
		clone.flip();
		return clone;
	}

	/**
	 * Clones the given {@link LongBuffer} into a new {@link LongBuffer}. <br>
	 * The original buffer is preserved.
	 * 
	 * @param original the {@link LongBuffer} to copy
	 * @return a clone of the given buffer
	 */
	public static LongBuffer cloneLongBuffer(LongBuffer original)
	{
		ObjectUtils.isNullThrow(original);
		LongBuffer clone = createLongBuffer(original.capacity());
		original.rewind();
		clone.put(original);
		original.rewind();
		clone.flip();
		return clone;
	}

	private static int getAllocationSize(int elements, int elementShift)
	{
		return elements << elementShift;
	}
}