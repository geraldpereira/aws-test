package fr.byob.aws.dynamodb;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodb.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodb.model.AttributeAction;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodb.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodb.model.DeleteItemRequest;
import com.amazonaws.services.dynamodb.model.DeleteItemResult;
import com.amazonaws.services.dynamodb.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodb.model.GetItemRequest;
import com.amazonaws.services.dynamodb.model.GetItemResult;
import com.amazonaws.services.dynamodb.model.Key;
import com.amazonaws.services.dynamodb.model.PutItemRequest;
import com.amazonaws.services.dynamodb.model.PutItemResult;
import com.amazonaws.services.dynamodb.model.ReturnValue;
import com.amazonaws.services.dynamodb.model.UpdateItemRequest;
import com.amazonaws.services.dynamodb.model.UpdateItemResult;


public class LowLevelItemCRUDExample {
    
    static AmazonDynamoDBClient client;
    static String tableName = "ProductCatalog";
    
    public static void main(String[] args) throws IOException {

        createClient();

        createItems();

        retrieveItem();

        // Perform various updates.
        updateMultipleAttributes();
        updateAddNewAttribute();
        updateExistingAttributeConditionally();

        // Delete the item.
        deleteItem();
        
    }

    private static void createClient() throws IOException {
        AWSCredentials credentials = new BasicAWSCredentials(
				"AKIAJZCQEUO77Y7D4K2A",
				"vp+SWwm5ppJaC5E01cUEkmt8bJYd00qldL9rpKdf"); 
//        		new PropertiesCredentials(
//                LowLevelItemCRUDExample.class.getResourceAsStream("AwsCredentials.properties"));

        client = new AmazonDynamoDBClient(credentials);
        client.setEndpoint("dynamodb.eu-west-1.amazonaws.com");
    }

    private static void createItems() {
        try {
            Map<String, AttributeValue> item1 = new HashMap<String, AttributeValue>();
            item1.put("Id", new AttributeValue().withN("120"));
            item1.put("Title", new AttributeValue().withS("Book 120 Title"));
            item1.put("ISBN", new AttributeValue().withS("120-1111111111"));
            item1.put("Authors", new AttributeValue()
                .withSS(Arrays.asList("Author12", "Author22")));
            item1.put("Price", new AttributeValue().withN("20.00"));
            item1.put("Category", new AttributeValue().withS("Book"));
            item1.put("Dimensions", new AttributeValue().withS("8.5x11.0x.75"));
            item1.put("InPublication", new AttributeValue().withN("0")); // false
            
            PutItemRequest putItemRequest1 = new PutItemRequest()
            .withTableName(tableName)
            .withItem(item1);
            PutItemResult result1 = client.putItem(putItemRequest1);
    
            Map<String, AttributeValue> item2 = new HashMap<String, AttributeValue>();
            item2.put("Id", new AttributeValue().withN("121"));
            item2.put("Title", new AttributeValue().withS("Book 121 Title"));
            item2.put("ISBN", new AttributeValue().withS("121-1111111111"));
            item2.put("Price", new AttributeValue().withN("20.00"));
            item2.put("ProductCategory", new AttributeValue().withS("Book"));
            item2.put("Authors", new AttributeValue()
                .withSS(Arrays.asList("Author21", "Author22")));
            item1.put("Dimensions", new AttributeValue().withS("8.5x11.0x.75"));
            item1.put("InPublication", new AttributeValue().withN("1"));
    
            PutItemRequest putItemRequest2 = new PutItemRequest()
                .withTableName(tableName)
                .withItem(item2);
            PutItemResult result2 = client.putItem(putItemRequest2);       
        } catch (AmazonServiceException ase) {
            System.err.println("Create items failed : "+ase.getMessage());
        } 
    }

    private static void retrieveItem() {
        try {
            
            GetItemRequest getItemRequest = new GetItemRequest()
                .withTableName(tableName)
                .withKey(new Key()
                    .withHashKeyElement(new AttributeValue().withN("120")))
                .withAttributesToGet(Arrays.asList("Id", "ISBN", "Title", "Authors"));
            
            GetItemResult result = client.getItem(getItemRequest);

            // Check the response.
            System.out.println("Printing item after retrieving it....");
            printItem(result.getItem());            
                        
        }  catch (AmazonServiceException ase) {
                    System.err.println("Failed to retrieve item in " + tableName);
        }   

    }

    private static void updateAddNewAttribute() {
        try {
            Map<String, AttributeValueUpdate> updateItems = 
                new HashMap<String, AttributeValueUpdate>();

            Key key = new Key().withHashKeyElement(new AttributeValue().withN("121"));
            updateItems.put("NewAttribute", 
                    new AttributeValueUpdate()
                        .withValue(new AttributeValue().withS("Some Value")));
            
            ReturnValue returnValues = ReturnValue.ALL_NEW;
            
            UpdateItemRequest updateItemRequest = new UpdateItemRequest()
                .withTableName(tableName)
                .withKey(key)
                .withAttributeUpdates(updateItems)
                .withReturnValues(returnValues);
            
            UpdateItemResult result = client.updateItem(updateItemRequest);
            
            // Check the response.
            System.out.println("Printing item after adding new attribute...");
            printItem(result.getAttributes());            
                            
        }   catch (AmazonServiceException ase) {
                    System.err.println("Failed to add new attribute in " + tableName);
        }        
    }
    
    private static void updateMultipleAttributes() {
        try {
            Map<String, AttributeValueUpdate> updateItems = 
                new HashMap<String, AttributeValueUpdate>();

            Key key = new Key().withHashKeyElement(new AttributeValue().withN("120"));
            // Add two new authors to the list.
            updateItems.put("Authors", 
                    new AttributeValueUpdate()
                        .withAction(AttributeAction.ADD)
                        .withValue(new AttributeValue().withSS("Author YY", "Author ZZ")));
            // Add a new attribute.
            updateItems.put("NewAttribute", 
                    new AttributeValueUpdate()
                        .withValue(new AttributeValue().withS("someValue")));
            
            ReturnValue returnValues = ReturnValue.ALL_NEW;
            
            UpdateItemRequest updateItemRequest = new UpdateItemRequest()
                .withTableName(tableName)
                .withKey(key)
                .withAttributeUpdates(updateItems)
                .withReturnValues(returnValues);
            
            UpdateItemResult result = client.updateItem(updateItemRequest);
            
            // Check the response.
            System.out.println("Printing item after multiple attribute update...");
            printItem(result.getAttributes());            
                            
        }   catch (AmazonServiceException ase) {
                    System.err.println("Failed to update multiple attributes in " + tableName);
        }
    }

    private static void updateExistingAttributeConditionally() {
        try {
            
            Map<String, AttributeValueUpdate> updateItems = new HashMap<String, AttributeValueUpdate>();
            Map<String, ExpectedAttributeValue> expectedValues = new HashMap<String, ExpectedAttributeValue>();

            Key key = new Key().withHashKeyElement(new AttributeValue().withN("120"));
            // Specify the desired price - 25.00
            updateItems.put("Price", 
                    new AttributeValueUpdate()
                        .withAction(AttributeAction.PUT)
                        .withValue(new AttributeValue().withN("25.00")));

           // This updates the price only if current price is 20.00.
            expectedValues.put("Price",
                    new ExpectedAttributeValue()
                        .withValue(new AttributeValue().withN("20.00")));

            ReturnValue returnValues = ReturnValue.ALL_NEW;

            UpdateItemRequest updateItemRequest = new UpdateItemRequest()
                .withTableName(tableName)
                .withKey(key)
                .withAttributeUpdates(updateItems)
                .withExpected(expectedValues)
                .withReturnValues(returnValues);


            UpdateItemResult result = client.updateItem(updateItemRequest);
            
            // Check the response.
            System.out.println("Printing item after conditional update to new attribute...");
            printItem(result.getAttributes());            
        } catch (ConditionalCheckFailedException cse) {
            // Reload object and retry code.
            System.err.println("Conditional check failed in " + tableName);
        } catch (AmazonServiceException ase) {
            System.err.println("Error updating item in " + tableName);
        }        
    }
    

    private static void deleteItem() {
        try {
            
            Map<String, ExpectedAttributeValue> expectedValues = new HashMap<String, ExpectedAttributeValue>();
            Key key = new Key().withHashKeyElement(new AttributeValue().withN("120"));

            expectedValues.put("InPublication",
                    new ExpectedAttributeValue()
                        .withValue(new AttributeValue().withN("0"))); // Boolean stored as 0 or 1.

            ReturnValue returnValues = ReturnValue.ALL_OLD;

            DeleteItemRequest deleteItemRequest = new DeleteItemRequest()
                .withTableName(tableName)
                .withKey(key)
                .withExpected(expectedValues)
                .withReturnValues(returnValues);

            DeleteItemResult result = client.deleteItem(deleteItemRequest);
            
            // Check the response.
            System.out.println("Printing item that was deleted...");
            printItem(result.getAttributes());            

                                    
        }  catch (AmazonServiceException ase) {
                                System.err.println("Failed to get item after deletion " + tableName);
        } 
        
    }

    private static void printItem(Map<String, AttributeValue> attributeList) {
        for (Map.Entry<String, AttributeValue> item : attributeList.entrySet()) {
            String attributeName = item.getKey();
            AttributeValue value = item.getValue();
            System.out.println(attributeName + " "
                    + (value.getS() == null ? "" : "S=[" + value.getS() + "]")
                    + (value.getN() == null ? "" : "N=[" + value.getN() + "]")
                    + (value.getB() == null ? "" : "B=[" + value.getB() + "]")
                    + (value.getSS() == null ? "" : "SS=[" + value.getSS() + "]")
                    + (value.getNS() == null ? "" : "NS=[" + value.getNS() + "]")
                    + (value.getBS() == null ? "" : "BS=[" + value.getBS() + "] \n"));
        }
    }
} 
