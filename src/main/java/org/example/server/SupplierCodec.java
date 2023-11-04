package org.example.server;
import org.bson.*;
import org.bson.codecs.*;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;
import org.example.model.Supplier;

public class SupplierCodec implements Codec<Supplier> {

    @Override
    public void encode(final BsonWriter writer, final Supplier supplier, final EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeObjectId("_id", new ObjectId()); // Assuming you want to auto-generate an ObjectId for the recipient
        writer.writeInt32("id", supplier.getId());
        writer.writeString("firstName", supplier.getFirstName());
        writer.writeString("lastName", supplier.getLastName());
        writer.writeString("storeLocation", supplier.getStoreLocation());
        writer.writeEndDocument();
    }

    @Override
    public Supplier decode(final BsonReader reader, final DecoderContext decoderContext) {
        reader.readStartDocument();
        int id = reader.readInt32("id");
        String firstName = reader.readString("firstName");
        String lastName = reader.readString("lastName");
        String storeLocation = reader.readString("storeLocation");
        reader.readEndDocument();

        return new Supplier(id,firstName, lastName, storeLocation);
    }

    @Override
    public Class<Supplier> getEncoderClass() {
        return Supplier.class;
    }
}

