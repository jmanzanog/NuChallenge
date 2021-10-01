package authorizer;

import handler.PerformTxHandler;
import handler.PrintOutputHandler;
import handler.ValidateActiveAccountHandler;
import handler.ValidateAlreadyCreatedAccountHandler;
import handler.ValidateHighFrequencyTxHandler;
import handler.ValidateInitAccountHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import model.Record;
import pipeline.Pipeline;

import static container.RegexContainer.isAccountMatches;
import static container.RegexContainer.isTxMatches;


public final class NuAuthorizer {
    public static void main(String[] args) throws IOException {
       /*
       Reading and assigning records in json format from stdin
        */
        var records = new ArrayList<Record>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (isAccountMatches(line) || isTxMatches(line))
                    records.add(new Record(line));
                else
                    System.out.println("Json format or structure not supported");
            }
        }
         /*
         Pipeline configured with all handlers containing the required validations
         */
        var pipeline = new Pipeline<>(
                new ValidateAlreadyCreatedAccountHandler())
                .addHandler(new ValidateActiveAccountHandler())
                .addHandler(new ValidateInitAccountHandler())
                .addHandler(new ValidateHighFrequencyTxHandler())
                .addHandler(new PerformTxHandler())
                .addHandler(new PrintOutputHandler());

        /*
        pipeline execute for each entered record
         */
        records.stream().reduce(null, (accumRecord, currentRecord) -> {
            if (accumRecord != null && accumRecord.getAccount() != null) {
                currentRecord.setAccount(accumRecord.getAccount());
                currentRecord.setTxTraces(accumRecord.getTxTraces());
            }
            return pipeline.execute(currentRecord);
        });

    }
}
