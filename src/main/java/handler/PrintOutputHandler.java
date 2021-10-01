package handler;

import container.Serializer;
import model.Record;
import pipeline.Handler;
/***
 * Handler to print record output
 */
public final class PrintOutputHandler implements Handler<Record, Record> {

    @Override
    public Record process(Record input) {
        System.out.println(Serializer.toJson(input));
        return input;
    }
}
