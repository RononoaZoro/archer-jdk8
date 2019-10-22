package com.luo.lang.Anotation;

/**
 * The common interface extended by all annotation types.  Note that an
 * interface that manually extends this one does <i>not</i> define
 * an annotation type.  Also note that this interface does not itself
 * define an annotation type.
 * <p>
 * More information about annotation types can be found in section 9.6 of
 * <cite>The Java&trade; Language Specification</cite>.
 * <p>
 * The {@link java.lang.reflect.AnnotatedElement} interface discusses
 * compatibility concerns when evolving an annotation type from being
 * non-repeatable to being repeatable.
 *
 * @author Josh Bloch
 * @author luoxuzheng
 * @create 2019-10-22 9:43
 * @see java.lang.annotation.Annotation
 * @since 1.5
 **/
public interface ArchAnotation {

    /**
     * @see java.lang.annotation.Annotation#equals
     * @param obj
     * @return true if the specified object represents an annotation
     *         that is logically equivalent to this one, otherwise false
     */
    boolean equals(Object obj);

    /**
     * @see java.lang.annotation.Annotation#hashCode
     * @return the hash code of this annotation
     */
    int hashCode();

    /**
     * @see java.lang.annotation.Annotation#toString
     * @return a string representation of this annotation
     */
    String toString();

    /**
     *
     * @return the annotation type of this annotation
     */
    Class<? extends ArchAnotation> annotationType();

}
