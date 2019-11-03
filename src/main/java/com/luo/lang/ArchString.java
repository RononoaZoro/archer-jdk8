package com.luo.lang;

import com.luo.io.ArchSerializable;

import java.io.ObjectStreamField;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author luoxuzheng
 * @author Lee Boynton
 * @author Arthur van Hoff
 * @author Martin Buchholz
 * @author Ulf Zibis
 * @create 2019-10-22 22:33
 * @see java.lang.Object#toString()
 * @see java.lang.StringBuffer
 * @see java.lang.StringBuilder
 * @see java.nio.charset.Charset
 * @since JDK1.0
 **/
public final class ArchString implements ArchSerializable, ArchComparable<ArchString>, ArchCharSequence {

    /** The value is used for character storage. */
    private final char[] value;

    /** Cache the hash code for the string */
    private int hash; // Default to 0

    /** use serialVersionUID from JDK 1.0.2 for interoperability(互通性) */
    private static final long serialVersionUID = -6849794470754667710L;

    /**
     * Class ArchString is special cased within the Serialization Stream Protocol.
     *
     * A ArchString instance is written into an ObjectOutputStream according to
     * <a href="{@docRoot}/../platform/serialization/spec/output.html">
     * Object Serialization Specification, Section 6.2, "Stream Elements"</a>
     */
    private static final ObjectStreamField[] serialPersistentFields =
            new ObjectStreamField[0];

    /**
     * Initializes a newly created {@code ArchString} object so that it represents
     * an empty character sequence.  Note that use of this constructor is
     * unnecessary since Strings are immutable.
     */
    public ArchString() {
        char[] temp = "".toCharArray();
        this.value = temp;
    }

    public ArchString(ArchString original) {
        this.value = original.value;
        this.hash = original.hash;
    }

    public ArchString(char value[]) {
        this.value = Arrays.copyOf(value, value.length);
    }

    public ArchString(char value[], int offset, int count) {
        if (offset < 0) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        if (count <= 0) {
            if (count < 0) {
                throw new StringIndexOutOfBoundsException(count);
            }
            if (offset <= value.length) {
                char[] temp = "".toCharArray();
                this.value = temp;
                return;
            }
        }
        // Note: offset or count might be near -1>>>1.
        if (offset > value.length - count) {
            throw new StringIndexOutOfBoundsException(offset + count);
        }
        this.value = Arrays.copyOfRange(value, offset, offset+count);
    }

    public ArchString(int[] codePoints, int offset, int count) {
        if (offset < 0) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        if (count <= 0) {
            if (count < 0) {
                throw new StringIndexOutOfBoundsException(count);
            }
            if (offset <= codePoints.length) {
                char[] temp = "".toCharArray();
                this.value = temp;
                return;
            }
        }
        // Note: offset or count might be near -1>>>1.
        if (offset > codePoints.length - count) {
            throw new StringIndexOutOfBoundsException(offset + count);
        }

        final int end = offset + count;

        // Pass 1: Compute precise size of char[]
        int n = count;
        for (int i = offset; i < end; i++) {
            int c = codePoints[i];
            if (Character.isBmpCodePoint(c))
                continue;
            else if (Character.isValidCodePoint(c))
                n++;
            else throw new IllegalArgumentException(Integer.toString(c));
        }

        // Pass 2: Allocate and fill in char[]
        final char[] v = new char[n];

        for (int i = offset, j = 0; i < end; i++, j++) {
            int c = codePoints[i];
            if (Character.isBmpCodePoint(c))
                v[j] = (char)c;
            else
                ArchCharacter.toSurrogates(c, v, j++);
        }

        this.value = v;
    }

    /**
     * This object (which is already a string!) is itself returned.
     *
     * @return  the string itself.
     */
    public ArchString toArchString() {
        return this;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public ArchCharSequence subSequence(int start, int end) {
        return null;
    }

    @Override
    public IntStream chars() {
        return null;
    }

    @Override
    public IntStream codePoints() {
        return null;
    }

    @Override
    public int compareTo(ArchString o) {
        return 0;
    }


}
