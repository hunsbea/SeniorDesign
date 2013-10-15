package edu.utdallas.gamegenerator;

import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * User: clocke
 * Date: 3/31/13
 * Time: 10:19 PM
 */
public class MySchemaOutputResolver extends SchemaOutputResolver {
    private String filename;

    public MySchemaOutputResolver(String filename) {
        this.filename = filename;
    }

    public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
        File file = new File(filename);
        StreamResult result = new StreamResult(file);
        result.setSystemId(file.toURI().toURL().toString());
        return result;
    }

}