// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: YLBaseMessageModel.proto

package com.hoggen.sublimation.proto;

public final class BaseMessageModel {
  private BaseMessageModel() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface YLBaseMessageModelOrBuilder extends
      // @@protoc_insertion_point(interface_extends:YLBaseMessageModel)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     *消息头
     * </pre>
     *
     * <code>int32 title = 1;</code>
     */
    int getTitle();

    /**
     * <pre>
     *消息模块
     * </pre>
     *
     * <code>int32 module = 2;</code>
     */
    int getModule();

    /**
     * <pre>
     *消息命令
     * </pre>
     *
     * <code>int32 command = 3;</code>
     */
    int getCommand();

    /**
     * <pre>
     *消息体
     * </pre>
     *
     * <code>bytes data = 4;</code>
     */
    com.google.protobuf.ByteString getData();
  }
  /**
   * <pre>
   * 用户单聊信息模型
   * </pre>
   *
   * Protobuf type {@code YLBaseMessageModel}
   */
  public  static final class YLBaseMessageModel extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:YLBaseMessageModel)
      YLBaseMessageModelOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use YLBaseMessageModel.newBuilder() to construct.
    private YLBaseMessageModel(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private YLBaseMessageModel() {
      title_ = 0;
      module_ = 0;
      command_ = 0;
      data_ = com.google.protobuf.ByteString.EMPTY;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private YLBaseMessageModel(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new NullPointerException();
      }
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
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {

              title_ = input.readInt32();
              break;
            }
            case 16: {

              module_ = input.readInt32();
              break;
            }
            case 24: {

              command_ = input.readInt32();
              break;
            }
            case 34: {

              data_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return BaseMessageModel.internal_static_YLBaseMessageModel_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return BaseMessageModel.internal_static_YLBaseMessageModel_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              YLBaseMessageModel.class, Builder.class);
    }

    public static final int TITLE_FIELD_NUMBER = 1;
    private int title_;
    /**
     * <pre>
     *消息头
     * </pre>
     *
     * <code>int32 title = 1;</code>
     */
    public int getTitle() {
      return title_;
    }

    public static final int MODULE_FIELD_NUMBER = 2;
    private int module_;
    /**
     * <pre>
     *消息模块
     * </pre>
     *
     * <code>int32 module = 2;</code>
     */
    public int getModule() {
      return module_;
    }

    public static final int COMMAND_FIELD_NUMBER = 3;
    private int command_;
    /**
     * <pre>
     *消息命令
     * </pre>
     *
     * <code>int32 command = 3;</code>
     */
    public int getCommand() {
      return command_;
    }

    public static final int DATA_FIELD_NUMBER = 4;
    private com.google.protobuf.ByteString data_;
    /**
     * <pre>
     *消息体
     * </pre>
     *
     * <code>bytes data = 4;</code>
     */
    public com.google.protobuf.ByteString getData() {
      return data_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (title_ != 0) {
        output.writeInt32(1, title_);
      }
      if (module_ != 0) {
        output.writeInt32(2, module_);
      }
      if (command_ != 0) {
        output.writeInt32(3, command_);
      }
      if (!data_.isEmpty()) {
        output.writeBytes(4, data_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (title_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, title_);
      }
      if (module_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, module_);
      }
      if (command_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, command_);
      }
      if (!data_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(4, data_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof YLBaseMessageModel)) {
        return super.equals(obj);
      }
      YLBaseMessageModel other = (YLBaseMessageModel) obj;

      boolean result = true;
      result = result && (getTitle()
          == other.getTitle());
      result = result && (getModule()
          == other.getModule());
      result = result && (getCommand()
          == other.getCommand());
      result = result && getData()
          .equals(other.getData());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + TITLE_FIELD_NUMBER;
      hash = (53 * hash) + getTitle();
      hash = (37 * hash) + MODULE_FIELD_NUMBER;
      hash = (53 * hash) + getModule();
      hash = (37 * hash) + COMMAND_FIELD_NUMBER;
      hash = (53 * hash) + getCommand();
      hash = (37 * hash) + DATA_FIELD_NUMBER;
      hash = (53 * hash) + getData().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static YLBaseMessageModel parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static YLBaseMessageModel parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static YLBaseMessageModel parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static YLBaseMessageModel parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static YLBaseMessageModel parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static YLBaseMessageModel parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static YLBaseMessageModel parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static YLBaseMessageModel parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static YLBaseMessageModel parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static YLBaseMessageModel parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static YLBaseMessageModel parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static YLBaseMessageModel parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(YLBaseMessageModel prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     * 用户单聊信息模型
     * </pre>
     *
     * Protobuf type {@code YLBaseMessageModel}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:YLBaseMessageModel)
        YLBaseMessageModelOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return BaseMessageModel.internal_static_YLBaseMessageModel_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return BaseMessageModel.internal_static_YLBaseMessageModel_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                YLBaseMessageModel.class, Builder.class);
      }

      // Construct using com.hoggen.sublimation.proto.BaseMessageModel.YLBaseMessageModel.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        title_ = 0;

        module_ = 0;

        command_ = 0;

        data_ = com.google.protobuf.ByteString.EMPTY;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return BaseMessageModel.internal_static_YLBaseMessageModel_descriptor;
      }

      public YLBaseMessageModel getDefaultInstanceForType() {
        return YLBaseMessageModel.getDefaultInstance();
      }

      public YLBaseMessageModel build() {
        YLBaseMessageModel result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public YLBaseMessageModel buildPartial() {
        YLBaseMessageModel result = new YLBaseMessageModel(this);
        result.title_ = title_;
        result.module_ = module_;
        result.command_ = command_;
        result.data_ = data_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof YLBaseMessageModel) {
          return mergeFrom((YLBaseMessageModel)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(YLBaseMessageModel other) {
        if (other == YLBaseMessageModel.getDefaultInstance()) return this;
        if (other.getTitle() != 0) {
          setTitle(other.getTitle());
        }
        if (other.getModule() != 0) {
          setModule(other.getModule());
        }
        if (other.getCommand() != 0) {
          setCommand(other.getCommand());
        }
        if (other.getData() != com.google.protobuf.ByteString.EMPTY) {
          setData(other.getData());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        YLBaseMessageModel parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (YLBaseMessageModel) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int title_ ;
      /**
       * <pre>
       *消息头
       * </pre>
       *
       * <code>int32 title = 1;</code>
       */
      public int getTitle() {
        return title_;
      }
      /**
       * <pre>
       *消息头
       * </pre>
       *
       * <code>int32 title = 1;</code>
       */
      public Builder setTitle(int value) {
        
        title_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *消息头
       * </pre>
       *
       * <code>int32 title = 1;</code>
       */
      public Builder clearTitle() {
        
        title_ = 0;
        onChanged();
        return this;
      }

      private int module_ ;
      /**
       * <pre>
       *消息模块
       * </pre>
       *
       * <code>int32 module = 2;</code>
       */
      public int getModule() {
        return module_;
      }
      /**
       * <pre>
       *消息模块
       * </pre>
       *
       * <code>int32 module = 2;</code>
       */
      public Builder setModule(int value) {
        
        module_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *消息模块
       * </pre>
       *
       * <code>int32 module = 2;</code>
       */
      public Builder clearModule() {
        
        module_ = 0;
        onChanged();
        return this;
      }

      private int command_ ;
      /**
       * <pre>
       *消息命令
       * </pre>
       *
       * <code>int32 command = 3;</code>
       */
      public int getCommand() {
        return command_;
      }
      /**
       * <pre>
       *消息命令
       * </pre>
       *
       * <code>int32 command = 3;</code>
       */
      public Builder setCommand(int value) {
        
        command_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *消息命令
       * </pre>
       *
       * <code>int32 command = 3;</code>
       */
      public Builder clearCommand() {
        
        command_ = 0;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString data_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <pre>
       *消息体
       * </pre>
       *
       * <code>bytes data = 4;</code>
       */
      public com.google.protobuf.ByteString getData() {
        return data_;
      }
      /**
       * <pre>
       *消息体
       * </pre>
       *
       * <code>bytes data = 4;</code>
       */
      public Builder setData(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        data_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *消息体
       * </pre>
       *
       * <code>bytes data = 4;</code>
       */
      public Builder clearData() {
        
        data_ = getDefaultInstance().getData();
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:YLBaseMessageModel)
    }

    // @@protoc_insertion_point(class_scope:YLBaseMessageModel)
    private static final YLBaseMessageModel DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new YLBaseMessageModel();
    }

    public static YLBaseMessageModel getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<YLBaseMessageModel>
        PARSER = new com.google.protobuf.AbstractParser<YLBaseMessageModel>() {
      public YLBaseMessageModel parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new YLBaseMessageModel(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<YLBaseMessageModel> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<YLBaseMessageModel> getParserForType() {
      return PARSER;
    }

    public YLBaseMessageModel getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_YLBaseMessageModel_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_YLBaseMessageModel_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\030YLBaseMessageModel.proto\"R\n\022YLBaseMess" +
      "ageModel\022\r\n\005title\030\001 \001(\005\022\016\n\006module\030\002 \001(\005\022" +
      "\017\n\007command\030\003 \001(\005\022\014\n\004data\030\004 \001(\014B0\n\034com.ho" +
      "ggen.sublimation.protoB\020BaseMessageModel" +
      "b\006proto3"
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
        }, assigner);
    internal_static_YLBaseMessageModel_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_YLBaseMessageModel_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_YLBaseMessageModel_descriptor,
        new String[] { "Title", "Module", "Command", "Data", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
