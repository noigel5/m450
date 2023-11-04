package org.example.server;
import org.bson.*;
import org.bson.codecs.*;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;
import org.example.model.Package;

public class PackageCodec implements Codec<Package> {

    @Override
    public void encode(final BsonWriter writer, final Package aPackage, final EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeObjectId("_id", new ObjectId()); // Assuming you want to auto-generate an ObjectId for the recipient
        writer.writeInt32("id", aPackage.getId());
        writer.writeString("content", aPackage.getContent());
        writer.writeInt32("weight", aPackage.getWeight());
        writer.writeInt32("length", aPackage.getLength());
        writer.writeInt32("depth", aPackage.getDepth());
        writer.writeInt32("height", aPackage.getHeight());
        writer.writeInt32("recipientId", aPackage.getRecipientId());
        writer.writeEndDocument();
    }

    @Override
    public Package decode(final BsonReader reader, final DecoderContext decoderContext) {
        reader.readStartDocument();
        int id = reader.readInt32("id");
        String content = reader.readString("content");
        int weight = reader.readInt32("weight");
        int length = reader.readInt32("length");
        int depth = reader.readInt32("depth");
        int height = reader.readInt32("height");
        int recipientId = reader.readInt32("recipientId");
        reader.readEndDocument();

        return new Package(id, content, weight, length, depth, height, recipientId);
    }

    @Override
    public Class<Package> getEncoderClass() {
        return Package.class;
    }
}