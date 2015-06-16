package ScopeProtoJava;

//Generated by the protocol buffer compiler.  DO NOT EDIT!
//source: Temperature1NormalProto.proto

public final class Temperature1NormalProto {
private Temperature1NormalProto() {}
public static void registerAllExtensions(
   com.google.protobuf.ExtensionRegistry registry) {
}
public interface Temperature1NormalOrBuilder extends
   // @@protoc_insertion_point(interface_extends:Temperature1Normal)
   com.google.protobuf.MessageOrBuilder {

 /**
  * <code>required .EventHeader header = 1;</code>
  */
 boolean hasHeader();
 /**
  * <code>required .EventHeader header = 1;</code>
  */
 EventHeaderProto.EventHeader getHeader();
 /**
  * <code>required .EventHeader header = 1;</code>
  */
 EventHeaderProto.EventHeaderOrBuilder getHeaderOrBuilder();

 /**
  * <code>optional uint32 duration_seconds = 2;</code>
  *
  * <pre>
  * Duration in seconds
  * </pre>
  */
 boolean hasDurationSeconds();
 /**
  * <code>optional uint32 duration_seconds = 2;</code>
  *
  * <pre>
  * Duration in seconds
  * </pre>
  */
 int getDurationSeconds();

 /**
  * <code>optional int32 value_degrees = 3;</code>
  *
  * <pre>
  * Temperature in Celsius degrees
  * </pre>
  */
 boolean hasValueDegrees();
 /**
  * <code>optional int32 value_degrees = 3;</code>
  *
  * <pre>
  * Temperature in Celsius degrees
  * </pre>
  */
 int getValueDegrees();

 /**
  * <code>optional int32 high_threshold_degrees = 4;</code>
  *
  * <pre>
  * High threshold in Celsius degrees
  * </pre>
  */
 boolean hasHighThresholdDegrees();
 /**
  * <code>optional int32 high_threshold_degrees = 4;</code>
  *
  * <pre>
  * High threshold in Celsius degrees
  * </pre>
  */
 int getHighThresholdDegrees();

 /**
  * <code>optional int32 low_threshold_degrees = 5;</code>
  *
  * <pre>
  * Low threshold in Celsius degrees
  * </pre>
  */
 boolean hasLowThresholdDegrees();
 /**
  * <code>optional int32 low_threshold_degrees = 5;</code>
  *
  * <pre>
  * Low threshold in Celsius degrees
  * </pre>
  */
 int getLowThresholdDegrees();
}
/**
* Protobuf type {@code Temperature1Normal}
*
* <pre>
* Temperature 1 Normal
* </pre>
*/
public static final class Temperature1Normal extends
   com.google.protobuf.GeneratedMessage implements
   // @@protoc_insertion_point(message_implements:Temperature1Normal)
   Temperature1NormalOrBuilder {
 // Use Temperature1Normal.newBuilder() to construct.
 private Temperature1Normal(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
   super(builder);
   this.unknownFields = builder.getUnknownFields();
 }
 private Temperature1Normal(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

 private static final Temperature1Normal defaultInstance;
 public static Temperature1Normal getDefaultInstance() {
   return defaultInstance;
 }

 public Temperature1Normal getDefaultInstanceForType() {
   return defaultInstance;
 }

 private final com.google.protobuf.UnknownFieldSet unknownFields;
 @java.lang.Override
 public final com.google.protobuf.UnknownFieldSet
     getUnknownFields() {
   return this.unknownFields;
 }
 private Temperature1Normal(
     com.google.protobuf.CodedInputStream input,
     com.google.protobuf.ExtensionRegistryLite extensionRegistry)
     throws com.google.protobuf.InvalidProtocolBufferException {
   initFields();
   int mutable_bitField0_ = 0;
   com.google.protobuf.UnknownFieldSet.Builder unknownFields =
       com.google.protobuf.UnknownFieldSet.newBuilder();
   try {
     boolean done = false;
     while (!done) {
       int tag = input.readTag();
       switch (tag) {
         case 0:
           done = true;
           break;
         default: {
           if (!parseUnknownField(input, unknownFields,
                                  extensionRegistry, tag)) {
             done = true;
           }
           break;
         }
         case 10: {
           EventHeaderProto.EventHeader.Builder subBuilder = null;
           if (((bitField0_ & 0x00000001) == 0x00000001)) {
             subBuilder = header_.toBuilder();
           }
           header_ = input.readMessage(EventHeaderProto.EventHeader.PARSER, extensionRegistry);
           if (subBuilder != null) {
             subBuilder.mergeFrom(header_);
             header_ = subBuilder.buildPartial();
           }
           bitField0_ |= 0x00000001;
           break;
         }
         case 16: {
           bitField0_ |= 0x00000002;
           durationSeconds_ = input.readUInt32();
           break;
         }
         case 24: {
           bitField0_ |= 0x00000004;
           valueDegrees_ = input.readInt32();
           break;
         }
         case 32: {
           bitField0_ |= 0x00000008;
           highThresholdDegrees_ = input.readInt32();
           break;
         }
         case 40: {
           bitField0_ |= 0x00000010;
           lowThresholdDegrees_ = input.readInt32();
           break;
         }
       }
     }
   } catch (com.google.protobuf.InvalidProtocolBufferException e) {
     throw e.setUnfinishedMessage(this);
   } catch (java.io.IOException e) {
     throw new com.google.protobuf.InvalidProtocolBufferException(
         e.getMessage()).setUnfinishedMessage(this);
   } finally {
     this.unknownFields = unknownFields.build();
     makeExtensionsImmutable();
   }
 }
 public static final com.google.protobuf.Descriptors.Descriptor
     getDescriptor() {
   return Temperature1NormalProto.internal_static_Temperature1Normal_descriptor;
 }

 protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
     internalGetFieldAccessorTable() {
   return Temperature1NormalProto.internal_static_Temperature1Normal_fieldAccessorTable
       .ensureFieldAccessorsInitialized(
           Temperature1NormalProto.Temperature1Normal.class, Temperature1NormalProto.Temperature1Normal.Builder.class);
 }

 public static com.google.protobuf.Parser<Temperature1Normal> PARSER =
     new com.google.protobuf.AbstractParser<Temperature1Normal>() {
   public Temperature1Normal parsePartialFrom(
       com.google.protobuf.CodedInputStream input,
       com.google.protobuf.ExtensionRegistryLite extensionRegistry)
       throws com.google.protobuf.InvalidProtocolBufferException {
     return new Temperature1Normal(input, extensionRegistry);
   }
 };

 @java.lang.Override
 public com.google.protobuf.Parser<Temperature1Normal> getParserForType() {
   return PARSER;
 }

 private int bitField0_;
 public static final int HEADER_FIELD_NUMBER = 1;
 private EventHeaderProto.EventHeader header_;
 /**
  * <code>required .EventHeader header = 1;</code>
  */
 public boolean hasHeader() {
   return ((bitField0_ & 0x00000001) == 0x00000001);
 }
 /**
  * <code>required .EventHeader header = 1;</code>
  */
 public EventHeaderProto.EventHeader getHeader() {
   return header_;
 }
 /**
  * <code>required .EventHeader header = 1;</code>
  */
 public EventHeaderProto.EventHeaderOrBuilder getHeaderOrBuilder() {
   return header_;
 }

 public static final int DURATION_SECONDS_FIELD_NUMBER = 2;
 private int durationSeconds_;
 /**
  * <code>optional uint32 duration_seconds = 2;</code>
  *
  * <pre>
  * Duration in seconds
  * </pre>
  */
 public boolean hasDurationSeconds() {
   return ((bitField0_ & 0x00000002) == 0x00000002);
 }
 /**
  * <code>optional uint32 duration_seconds = 2;</code>
  *
  * <pre>
  * Duration in seconds
  * </pre>
  */
 public int getDurationSeconds() {
   return durationSeconds_;
 }

 public static final int VALUE_DEGREES_FIELD_NUMBER = 3;
 private int valueDegrees_;
 /**
  * <code>optional int32 value_degrees = 3;</code>
  *
  * <pre>
  * Temperature in Celsius degrees
  * </pre>
  */
 public boolean hasValueDegrees() {
   return ((bitField0_ & 0x00000004) == 0x00000004);
 }
 /**
  * <code>optional int32 value_degrees = 3;</code>
  *
  * <pre>
  * Temperature in Celsius degrees
  * </pre>
  */
 public int getValueDegrees() {
   return valueDegrees_;
 }

 public static final int HIGH_THRESHOLD_DEGREES_FIELD_NUMBER = 4;
 private int highThresholdDegrees_;
 /**
  * <code>optional int32 high_threshold_degrees = 4;</code>
  *
  * <pre>
  * High threshold in Celsius degrees
  * </pre>
  */
 public boolean hasHighThresholdDegrees() {
   return ((bitField0_ & 0x00000008) == 0x00000008);
 }
 /**
  * <code>optional int32 high_threshold_degrees = 4;</code>
  *
  * <pre>
  * High threshold in Celsius degrees
  * </pre>
  */
 public int getHighThresholdDegrees() {
   return highThresholdDegrees_;
 }

 public static final int LOW_THRESHOLD_DEGREES_FIELD_NUMBER = 5;
 private int lowThresholdDegrees_;
 /**
  * <code>optional int32 low_threshold_degrees = 5;</code>
  *
  * <pre>
  * Low threshold in Celsius degrees
  * </pre>
  */
 public boolean hasLowThresholdDegrees() {
   return ((bitField0_ & 0x00000010) == 0x00000010);
 }
 /**
  * <code>optional int32 low_threshold_degrees = 5;</code>
  *
  * <pre>
  * Low threshold in Celsius degrees
  * </pre>
  */
 public int getLowThresholdDegrees() {
   return lowThresholdDegrees_;
 }

 private void initFields() {
   header_ = EventHeaderProto.EventHeader.getDefaultInstance();
   durationSeconds_ = 0;
   valueDegrees_ = 0;
   highThresholdDegrees_ = 0;
   lowThresholdDegrees_ = 0;
 }
 private byte memoizedIsInitialized = -1;
 public final boolean isInitialized() {
   byte isInitialized = memoizedIsInitialized;
   if (isInitialized == 1) return true;
   if (isInitialized == 0) return false;

   if (!hasHeader()) {
     memoizedIsInitialized = 0;
     return false;
   }
   if (!getHeader().isInitialized()) {
     memoizedIsInitialized = 0;
     return false;
   }
   memoizedIsInitialized = 1;
   return true;
 }

 public void writeTo(com.google.protobuf.CodedOutputStream output)
                     throws java.io.IOException {
   getSerializedSize();
   if (((bitField0_ & 0x00000001) == 0x00000001)) {
     output.writeMessage(1, header_);
   }
   if (((bitField0_ & 0x00000002) == 0x00000002)) {
     output.writeUInt32(2, durationSeconds_);
   }
   if (((bitField0_ & 0x00000004) == 0x00000004)) {
     output.writeInt32(3, valueDegrees_);
   }
   if (((bitField0_ & 0x00000008) == 0x00000008)) {
     output.writeInt32(4, highThresholdDegrees_);
   }
   if (((bitField0_ & 0x00000010) == 0x00000010)) {
     output.writeInt32(5, lowThresholdDegrees_);
   }
   getUnknownFields().writeTo(output);
 }

 private int memoizedSerializedSize = -1;
 public int getSerializedSize() {
   int size = memoizedSerializedSize;
   if (size != -1) return size;

   size = 0;
   if (((bitField0_ & 0x00000001) == 0x00000001)) {
     size += com.google.protobuf.CodedOutputStream
       .computeMessageSize(1, header_);
   }
   if (((bitField0_ & 0x00000002) == 0x00000002)) {
     size += com.google.protobuf.CodedOutputStream
       .computeUInt32Size(2, durationSeconds_);
   }
   if (((bitField0_ & 0x00000004) == 0x00000004)) {
     size += com.google.protobuf.CodedOutputStream
       .computeInt32Size(3, valueDegrees_);
   }
   if (((bitField0_ & 0x00000008) == 0x00000008)) {
     size += com.google.protobuf.CodedOutputStream
       .computeInt32Size(4, highThresholdDegrees_);
   }
   if (((bitField0_ & 0x00000010) == 0x00000010)) {
     size += com.google.protobuf.CodedOutputStream
       .computeInt32Size(5, lowThresholdDegrees_);
   }
   size += getUnknownFields().getSerializedSize();
   memoizedSerializedSize = size;
   return size;
 }

 private static final long serialVersionUID = 0L;
 @java.lang.Override
 protected java.lang.Object writeReplace()
     throws java.io.ObjectStreamException {
   return super.writeReplace();
 }

 public static Temperature1NormalProto.Temperature1Normal parseFrom(
     com.google.protobuf.ByteString data)
     throws com.google.protobuf.InvalidProtocolBufferException {
   return PARSER.parseFrom(data);
 }
 public static Temperature1NormalProto.Temperature1Normal parseFrom(
     com.google.protobuf.ByteString data,
     com.google.protobuf.ExtensionRegistryLite extensionRegistry)
     throws com.google.protobuf.InvalidProtocolBufferException {
   return PARSER.parseFrom(data, extensionRegistry);
 }
 public static Temperature1NormalProto.Temperature1Normal parseFrom(byte[] data)
     throws com.google.protobuf.InvalidProtocolBufferException {
   return PARSER.parseFrom(data);
 }
 public static Temperature1NormalProto.Temperature1Normal parseFrom(
     byte[] data,
     com.google.protobuf.ExtensionRegistryLite extensionRegistry)
     throws com.google.protobuf.InvalidProtocolBufferException {
   return PARSER.parseFrom(data, extensionRegistry);
 }
 public static Temperature1NormalProto.Temperature1Normal parseFrom(java.io.InputStream input)
     throws java.io.IOException {
   return PARSER.parseFrom(input);
 }
 public static Temperature1NormalProto.Temperature1Normal parseFrom(
     java.io.InputStream input,
     com.google.protobuf.ExtensionRegistryLite extensionRegistry)
     throws java.io.IOException {
   return PARSER.parseFrom(input, extensionRegistry);
 }
 public static Temperature1NormalProto.Temperature1Normal parseDelimitedFrom(java.io.InputStream input)
     throws java.io.IOException {
   return PARSER.parseDelimitedFrom(input);
 }
 public static Temperature1NormalProto.Temperature1Normal parseDelimitedFrom(
     java.io.InputStream input,
     com.google.protobuf.ExtensionRegistryLite extensionRegistry)
     throws java.io.IOException {
   return PARSER.parseDelimitedFrom(input, extensionRegistry);
 }
 public static Temperature1NormalProto.Temperature1Normal parseFrom(
     com.google.protobuf.CodedInputStream input)
     throws java.io.IOException {
   return PARSER.parseFrom(input);
 }
 public static Temperature1NormalProto.Temperature1Normal parseFrom(
     com.google.protobuf.CodedInputStream input,
     com.google.protobuf.ExtensionRegistryLite extensionRegistry)
     throws java.io.IOException {
   return PARSER.parseFrom(input, extensionRegistry);
 }

 public static Builder newBuilder() { return Builder.create(); }
 public Builder newBuilderForType() { return newBuilder(); }
 public static Builder newBuilder(Temperature1NormalProto.Temperature1Normal prototype) {
   return newBuilder().mergeFrom(prototype);
 }
 public Builder toBuilder() { return newBuilder(this); }

 @java.lang.Override
 protected Builder newBuilderForType(
     com.google.protobuf.GeneratedMessage.BuilderParent parent) {
   Builder builder = new Builder(parent);
   return builder;
 }
 /**
  * Protobuf type {@code Temperature1Normal}
  *
  * <pre>
  * Temperature 1 Normal
  * </pre>
  */
 public static final class Builder extends
     com.google.protobuf.GeneratedMessage.Builder<Builder> implements
     // @@protoc_insertion_point(builder_implements:Temperature1Normal)
     Temperature1NormalProto.Temperature1NormalOrBuilder {
   public static final com.google.protobuf.Descriptors.Descriptor
       getDescriptor() {
     return Temperature1NormalProto.internal_static_Temperature1Normal_descriptor;
   }

   protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
       internalGetFieldAccessorTable() {
     return Temperature1NormalProto.internal_static_Temperature1Normal_fieldAccessorTable
         .ensureFieldAccessorsInitialized(
             Temperature1NormalProto.Temperature1Normal.class, Temperature1NormalProto.Temperature1Normal.Builder.class);
   }

   // Construct using Temperature1NormalProto.Temperature1Normal.newBuilder()
   private Builder() {
     maybeForceBuilderInitialization();
   }

   private Builder(
       com.google.protobuf.GeneratedMessage.BuilderParent parent) {
     super(parent);
     maybeForceBuilderInitialization();
   }
   private void maybeForceBuilderInitialization() {
     if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
       getHeaderFieldBuilder();
     }
   }
   private static Builder create() {
     return new Builder();
   }

   public Builder clear() {
     super.clear();
     if (headerBuilder_ == null) {
       header_ = EventHeaderProto.EventHeader.getDefaultInstance();
     } else {
       headerBuilder_.clear();
     }
     bitField0_ = (bitField0_ & ~0x00000001);
     durationSeconds_ = 0;
     bitField0_ = (bitField0_ & ~0x00000002);
     valueDegrees_ = 0;
     bitField0_ = (bitField0_ & ~0x00000004);
     highThresholdDegrees_ = 0;
     bitField0_ = (bitField0_ & ~0x00000008);
     lowThresholdDegrees_ = 0;
     bitField0_ = (bitField0_ & ~0x00000010);
     return this;
   }

   public Builder clone() {
     return create().mergeFrom(buildPartial());
   }

   public com.google.protobuf.Descriptors.Descriptor
       getDescriptorForType() {
     return Temperature1NormalProto.internal_static_Temperature1Normal_descriptor;
   }

   public Temperature1NormalProto.Temperature1Normal getDefaultInstanceForType() {
     return Temperature1NormalProto.Temperature1Normal.getDefaultInstance();
   }

   public Temperature1NormalProto.Temperature1Normal build() {
     Temperature1NormalProto.Temperature1Normal result = buildPartial();
     if (!result.isInitialized()) {
       throw newUninitializedMessageException(result);
     }
     return result;
   }

   public Temperature1NormalProto.Temperature1Normal buildPartial() {
     Temperature1NormalProto.Temperature1Normal result = new Temperature1NormalProto.Temperature1Normal(this);
     int from_bitField0_ = bitField0_;
     int to_bitField0_ = 0;
     if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
       to_bitField0_ |= 0x00000001;
     }
     if (headerBuilder_ == null) {
       result.header_ = header_;
     } else {
       result.header_ = headerBuilder_.build();
     }
     if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
       to_bitField0_ |= 0x00000002;
     }
     result.durationSeconds_ = durationSeconds_;
     if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
       to_bitField0_ |= 0x00000004;
     }
     result.valueDegrees_ = valueDegrees_;
     if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
       to_bitField0_ |= 0x00000008;
     }
     result.highThresholdDegrees_ = highThresholdDegrees_;
     if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
       to_bitField0_ |= 0x00000010;
     }
     result.lowThresholdDegrees_ = lowThresholdDegrees_;
     result.bitField0_ = to_bitField0_;
     onBuilt();
     return result;
   }

   public Builder mergeFrom(com.google.protobuf.Message other) {
     if (other instanceof Temperature1NormalProto.Temperature1Normal) {
       return mergeFrom((Temperature1NormalProto.Temperature1Normal)other);
     } else {
       super.mergeFrom(other);
       return this;
     }
   }

   public Builder mergeFrom(Temperature1NormalProto.Temperature1Normal other) {
     if (other == Temperature1NormalProto.Temperature1Normal.getDefaultInstance()) return this;
     if (other.hasHeader()) {
       mergeHeader(other.getHeader());
     }
     if (other.hasDurationSeconds()) {
       setDurationSeconds(other.getDurationSeconds());
     }
     if (other.hasValueDegrees()) {
       setValueDegrees(other.getValueDegrees());
     }
     if (other.hasHighThresholdDegrees()) {
       setHighThresholdDegrees(other.getHighThresholdDegrees());
     }
     if (other.hasLowThresholdDegrees()) {
       setLowThresholdDegrees(other.getLowThresholdDegrees());
     }
     this.mergeUnknownFields(other.getUnknownFields());
     return this;
   }

   public final boolean isInitialized() {
     if (!hasHeader()) {
       
       return false;
     }
     if (!getHeader().isInitialized()) {
       
       return false;
     }
     return true;
   }

   public Builder mergeFrom(
       com.google.protobuf.CodedInputStream input,
       com.google.protobuf.ExtensionRegistryLite extensionRegistry)
       throws java.io.IOException {
     Temperature1NormalProto.Temperature1Normal parsedMessage = null;
     try {
       parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
     } catch (com.google.protobuf.InvalidProtocolBufferException e) {
       parsedMessage = (Temperature1NormalProto.Temperature1Normal) e.getUnfinishedMessage();
       throw e;
     } finally {
       if (parsedMessage != null) {
         mergeFrom(parsedMessage);
       }
     }
     return this;
   }
   private int bitField0_;

   private EventHeaderProto.EventHeader header_ = EventHeaderProto.EventHeader.getDefaultInstance();
   private com.google.protobuf.SingleFieldBuilder<
       EventHeaderProto.EventHeader, EventHeaderProto.EventHeader.Builder, EventHeaderProto.EventHeaderOrBuilder> headerBuilder_;
   /**
    * <code>required .EventHeader header = 1;</code>
    */
   public boolean hasHeader() {
     return ((bitField0_ & 0x00000001) == 0x00000001);
   }
   /**
    * <code>required .EventHeader header = 1;</code>
    */
   public EventHeaderProto.EventHeader getHeader() {
     if (headerBuilder_ == null) {
       return header_;
     } else {
       return headerBuilder_.getMessage();
     }
   }
   /**
    * <code>required .EventHeader header = 1;</code>
    */
   public Builder setHeader(EventHeaderProto.EventHeader value) {
     if (headerBuilder_ == null) {
       if (value == null) {
         throw new NullPointerException();
       }
       header_ = value;
       onChanged();
     } else {
       headerBuilder_.setMessage(value);
     }
     bitField0_ |= 0x00000001;
     return this;
   }
   /**
    * <code>required .EventHeader header = 1;</code>
    */
   public Builder setHeader(
       EventHeaderProto.EventHeader.Builder builderForValue) {
     if (headerBuilder_ == null) {
       header_ = builderForValue.build();
       onChanged();
     } else {
       headerBuilder_.setMessage(builderForValue.build());
     }
     bitField0_ |= 0x00000001;
     return this;
   }
   /**
    * <code>required .EventHeader header = 1;</code>
    */
   public Builder mergeHeader(EventHeaderProto.EventHeader value) {
     if (headerBuilder_ == null) {
       if (((bitField0_ & 0x00000001) == 0x00000001) &&
           header_ != EventHeaderProto.EventHeader.getDefaultInstance()) {
         header_ =
           EventHeaderProto.EventHeader.newBuilder(header_).mergeFrom(value).buildPartial();
       } else {
         header_ = value;
       }
       onChanged();
     } else {
       headerBuilder_.mergeFrom(value);
     }
     bitField0_ |= 0x00000001;
     return this;
   }
   /**
    * <code>required .EventHeader header = 1;</code>
    */
   public Builder clearHeader() {
     if (headerBuilder_ == null) {
       header_ = EventHeaderProto.EventHeader.getDefaultInstance();
       onChanged();
     } else {
       headerBuilder_.clear();
     }
     bitField0_ = (bitField0_ & ~0x00000001);
     return this;
   }
   /**
    * <code>required .EventHeader header = 1;</code>
    */
   public EventHeaderProto.EventHeader.Builder getHeaderBuilder() {
     bitField0_ |= 0x00000001;
     onChanged();
     return getHeaderFieldBuilder().getBuilder();
   }
   /**
    * <code>required .EventHeader header = 1;</code>
    */
   public EventHeaderProto.EventHeaderOrBuilder getHeaderOrBuilder() {
     if (headerBuilder_ != null) {
       return headerBuilder_.getMessageOrBuilder();
     } else {
       return header_;
     }
   }
   /**
    * <code>required .EventHeader header = 1;</code>
    */
   private com.google.protobuf.SingleFieldBuilder<
       EventHeaderProto.EventHeader, EventHeaderProto.EventHeader.Builder, EventHeaderProto.EventHeaderOrBuilder> 
       getHeaderFieldBuilder() {
     if (headerBuilder_ == null) {
       headerBuilder_ = new com.google.protobuf.SingleFieldBuilder<
           EventHeaderProto.EventHeader, EventHeaderProto.EventHeader.Builder, EventHeaderProto.EventHeaderOrBuilder>(
               getHeader(),
               getParentForChildren(),
               isClean());
       header_ = null;
     }
     return headerBuilder_;
   }

   private int durationSeconds_ ;
   /**
    * <code>optional uint32 duration_seconds = 2;</code>
    *
    * <pre>
    * Duration in seconds
    * </pre>
    */
   public boolean hasDurationSeconds() {
     return ((bitField0_ & 0x00000002) == 0x00000002);
   }
   /**
    * <code>optional uint32 duration_seconds = 2;</code>
    *
    * <pre>
    * Duration in seconds
    * </pre>
    */
   public int getDurationSeconds() {
     return durationSeconds_;
   }
   /**
    * <code>optional uint32 duration_seconds = 2;</code>
    *
    * <pre>
    * Duration in seconds
    * </pre>
    */
   public Builder setDurationSeconds(int value) {
     bitField0_ |= 0x00000002;
     durationSeconds_ = value;
     onChanged();
     return this;
   }
   /**
    * <code>optional uint32 duration_seconds = 2;</code>
    *
    * <pre>
    * Duration in seconds
    * </pre>
    */
   public Builder clearDurationSeconds() {
     bitField0_ = (bitField0_ & ~0x00000002);
     durationSeconds_ = 0;
     onChanged();
     return this;
   }

   private int valueDegrees_ ;
   /**
    * <code>optional int32 value_degrees = 3;</code>
    *
    * <pre>
    * Temperature in Celsius degrees
    * </pre>
    */
   public boolean hasValueDegrees() {
     return ((bitField0_ & 0x00000004) == 0x00000004);
   }
   /**
    * <code>optional int32 value_degrees = 3;</code>
    *
    * <pre>
    * Temperature in Celsius degrees
    * </pre>
    */
   public int getValueDegrees() {
     return valueDegrees_;
   }
   /**
    * <code>optional int32 value_degrees = 3;</code>
    *
    * <pre>
    * Temperature in Celsius degrees
    * </pre>
    */
   public Builder setValueDegrees(int value) {
     bitField0_ |= 0x00000004;
     valueDegrees_ = value;
     onChanged();
     return this;
   }
   /**
    * <code>optional int32 value_degrees = 3;</code>
    *
    * <pre>
    * Temperature in Celsius degrees
    * </pre>
    */
   public Builder clearValueDegrees() {
     bitField0_ = (bitField0_ & ~0x00000004);
     valueDegrees_ = 0;
     onChanged();
     return this;
   }

   private int highThresholdDegrees_ ;
   /**
    * <code>optional int32 high_threshold_degrees = 4;</code>
    *
    * <pre>
    * High threshold in Celsius degrees
    * </pre>
    */
   public boolean hasHighThresholdDegrees() {
     return ((bitField0_ & 0x00000008) == 0x00000008);
   }
   /**
    * <code>optional int32 high_threshold_degrees = 4;</code>
    *
    * <pre>
    * High threshold in Celsius degrees
    * </pre>
    */
   public int getHighThresholdDegrees() {
     return highThresholdDegrees_;
   }
   /**
    * <code>optional int32 high_threshold_degrees = 4;</code>
    *
    * <pre>
    * High threshold in Celsius degrees
    * </pre>
    */
   public Builder setHighThresholdDegrees(int value) {
     bitField0_ |= 0x00000008;
     highThresholdDegrees_ = value;
     onChanged();
     return this;
   }
   /**
    * <code>optional int32 high_threshold_degrees = 4;</code>
    *
    * <pre>
    * High threshold in Celsius degrees
    * </pre>
    */
   public Builder clearHighThresholdDegrees() {
     bitField0_ = (bitField0_ & ~0x00000008);
     highThresholdDegrees_ = 0;
     onChanged();
     return this;
   }

   private int lowThresholdDegrees_ ;
   /**
    * <code>optional int32 low_threshold_degrees = 5;</code>
    *
    * <pre>
    * Low threshold in Celsius degrees
    * </pre>
    */
   public boolean hasLowThresholdDegrees() {
     return ((bitField0_ & 0x00000010) == 0x00000010);
   }
   /**
    * <code>optional int32 low_threshold_degrees = 5;</code>
    *
    * <pre>
    * Low threshold in Celsius degrees
    * </pre>
    */
   public int getLowThresholdDegrees() {
     return lowThresholdDegrees_;
   }
   /**
    * <code>optional int32 low_threshold_degrees = 5;</code>
    *
    * <pre>
    * Low threshold in Celsius degrees
    * </pre>
    */
   public Builder setLowThresholdDegrees(int value) {
     bitField0_ |= 0x00000010;
     lowThresholdDegrees_ = value;
     onChanged();
     return this;
   }
   /**
    * <code>optional int32 low_threshold_degrees = 5;</code>
    *
    * <pre>
    * Low threshold in Celsius degrees
    * </pre>
    */
   public Builder clearLowThresholdDegrees() {
     bitField0_ = (bitField0_ & ~0x00000010);
     lowThresholdDegrees_ = 0;
     onChanged();
     return this;
   }

   // @@protoc_insertion_point(builder_scope:Temperature1Normal)
 }

 static {
   defaultInstance = new Temperature1Normal(true);
   defaultInstance.initFields();
 }

 // @@protoc_insertion_point(class_scope:Temperature1Normal)
}

private static final com.google.protobuf.Descriptors.Descriptor
 internal_static_Temperature1Normal_descriptor;
private static
 com.google.protobuf.GeneratedMessage.FieldAccessorTable
   internal_static_Temperature1Normal_fieldAccessorTable;

public static com.google.protobuf.Descriptors.FileDescriptor
   getDescriptor() {
 return descriptor;
}
private static com.google.protobuf.Descriptors.FileDescriptor
   descriptor;
static {
 java.lang.String[] descriptorData = {
   "\n\035Temperature1NormalProto.proto\032\026EventHe" +
   "aderProto.proto\"\242\001\n\022Temperature1Normal\022\034" +
   "\n\006header\030\001 \002(\0132\014.EventHeader\022\030\n\020duration" +
   "_seconds\030\002 \001(\r\022\025\n\rvalue_degrees\030\003 \001(\005\022\036\n" +
   "\026high_threshold_degrees\030\004 \001(\005\022\035\n\025low_thr" +
   "eshold_degrees\030\005 \001(\005"
 };
 com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
     new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
       public com.google.protobuf.ExtensionRegistry assignDescriptors(
           com.google.protobuf.Descriptors.FileDescriptor root) {
         descriptor = root;
         return null;
       }
     };
 com.google.protobuf.Descriptors.FileDescriptor
   .internalBuildGeneratedFileFrom(descriptorData,
     new com.google.protobuf.Descriptors.FileDescriptor[] {
       EventHeaderProto.getDescriptor(),
     }, assigner);
 internal_static_Temperature1Normal_descriptor =
   getDescriptor().getMessageTypes().get(0);
 internal_static_Temperature1Normal_fieldAccessorTable = new
   com.google.protobuf.GeneratedMessage.FieldAccessorTable(
     internal_static_Temperature1Normal_descriptor,
     new java.lang.String[] { "Header", "DurationSeconds", "ValueDegrees", "HighThresholdDegrees", "LowThresholdDegrees", });
 EventHeaderProto.getDescriptor();
}

// @@protoc_insertion_point(outer_class_scope)
}
