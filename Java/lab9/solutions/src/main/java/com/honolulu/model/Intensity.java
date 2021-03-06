/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.honolulu.model;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Intensity extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -3427964808735971451L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Intensity\",\"namespace\":\"com.honolulu.model\",\"fields\":[{\"name\":\"scale\",\"type\":\"string\"},{\"name\":\"measurement\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Intensity> ENCODER =
      new BinaryMessageEncoder<Intensity>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Intensity> DECODER =
      new BinaryMessageDecoder<Intensity>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Intensity> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Intensity> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Intensity> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Intensity>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Intensity to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Intensity from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Intensity instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Intensity fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence scale;
  @Deprecated public int measurement;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Intensity() {}

  /**
   * All-args constructor.
   * @param scale The new value for scale
   * @param measurement The new value for measurement
   */
  public Intensity(java.lang.CharSequence scale, java.lang.Integer measurement) {
    this.scale = scale;
    this.measurement = measurement;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return scale;
    case 1: return measurement;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: scale = (java.lang.CharSequence)value$; break;
    case 1: measurement = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'scale' field.
   * @return The value of the 'scale' field.
   */
  public java.lang.CharSequence getScale() {
    return scale;
  }


  /**
   * Sets the value of the 'scale' field.
   * @param value the value to set.
   */
  public void setScale(java.lang.CharSequence value) {
    this.scale = value;
  }

  /**
   * Gets the value of the 'measurement' field.
   * @return The value of the 'measurement' field.
   */
  public int getMeasurement() {
    return measurement;
  }


  /**
   * Sets the value of the 'measurement' field.
   * @param value the value to set.
   */
  public void setMeasurement(int value) {
    this.measurement = value;
  }

  /**
   * Creates a new Intensity RecordBuilder.
   * @return A new Intensity RecordBuilder
   */
  public static com.honolulu.model.Intensity.Builder newBuilder() {
    return new com.honolulu.model.Intensity.Builder();
  }

  /**
   * Creates a new Intensity RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Intensity RecordBuilder
   */
  public static com.honolulu.model.Intensity.Builder newBuilder(com.honolulu.model.Intensity.Builder other) {
    if (other == null) {
      return new com.honolulu.model.Intensity.Builder();
    } else {
      return new com.honolulu.model.Intensity.Builder(other);
    }
  }

  /**
   * Creates a new Intensity RecordBuilder by copying an existing Intensity instance.
   * @param other The existing instance to copy.
   * @return A new Intensity RecordBuilder
   */
  public static com.honolulu.model.Intensity.Builder newBuilder(com.honolulu.model.Intensity other) {
    if (other == null) {
      return new com.honolulu.model.Intensity.Builder();
    } else {
      return new com.honolulu.model.Intensity.Builder(other);
    }
  }

  /**
   * RecordBuilder for Intensity instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Intensity>
    implements org.apache.avro.data.RecordBuilder<Intensity> {

    private java.lang.CharSequence scale;
    private int measurement;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.honolulu.model.Intensity.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.scale)) {
        this.scale = data().deepCopy(fields()[0].schema(), other.scale);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.measurement)) {
        this.measurement = data().deepCopy(fields()[1].schema(), other.measurement);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing Intensity instance
     * @param other The existing instance to copy.
     */
    private Builder(com.honolulu.model.Intensity other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.scale)) {
        this.scale = data().deepCopy(fields()[0].schema(), other.scale);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.measurement)) {
        this.measurement = data().deepCopy(fields()[1].schema(), other.measurement);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'scale' field.
      * @return The value.
      */
    public java.lang.CharSequence getScale() {
      return scale;
    }


    /**
      * Sets the value of the 'scale' field.
      * @param value The value of 'scale'.
      * @return This builder.
      */
    public com.honolulu.model.Intensity.Builder setScale(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.scale = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'scale' field has been set.
      * @return True if the 'scale' field has been set, false otherwise.
      */
    public boolean hasScale() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'scale' field.
      * @return This builder.
      */
    public com.honolulu.model.Intensity.Builder clearScale() {
      scale = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'measurement' field.
      * @return The value.
      */
    public int getMeasurement() {
      return measurement;
    }


    /**
      * Sets the value of the 'measurement' field.
      * @param value The value of 'measurement'.
      * @return This builder.
      */
    public com.honolulu.model.Intensity.Builder setMeasurement(int value) {
      validate(fields()[1], value);
      this.measurement = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'measurement' field has been set.
      * @return True if the 'measurement' field has been set, false otherwise.
      */
    public boolean hasMeasurement() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'measurement' field.
      * @return This builder.
      */
    public com.honolulu.model.Intensity.Builder clearMeasurement() {
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Intensity build() {
      try {
        Intensity record = new Intensity();
        record.scale = fieldSetFlags()[0] ? this.scale : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.measurement = fieldSetFlags()[1] ? this.measurement : (java.lang.Integer) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Intensity>
    WRITER$ = (org.apache.avro.io.DatumWriter<Intensity>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Intensity>
    READER$ = (org.apache.avro.io.DatumReader<Intensity>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.scale);

    out.writeInt(this.measurement);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.scale = in.readString(this.scale instanceof Utf8 ? (Utf8)this.scale : null);

      this.measurement = in.readInt();

    } else {
      for (int i = 0; i < 2; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.scale = in.readString(this.scale instanceof Utf8 ? (Utf8)this.scale : null);
          break;

        case 1:
          this.measurement = in.readInt();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










