package Address.addressbookdao;

import Address.DTO.Address;

import java.io.*;
import java.util.*;

public class AddressBookDaoImpl implements AddressBookDao {
    private Map<String, Address> addressBook;
    public static final String ROSTER_FILE = "/Users/jeff/IdeaProjects/Address/src/Address/roster.txt";
    public static final String DELIMITER = "::";

    public AddressBookDaoImpl(){
        this.addressBook = new HashMap<>();
    }

    @Override
    public Address addAddress(String lastName, Address address) throws AddressBookDaoException {
        loadAddress();
        Address currentAddress = this.addressBook.put(lastName, address);
        writeAddress();
        return currentAddress;
    }

    @Override
    public List<Address> getAllAddresses()  {
        return new ArrayList<Address>(this.addressBook.values());
    }

    @Override
    public Address getAddress(String lastName) throws AddressBookDaoException {
        loadAddress();
        return this.addressBook.get(lastName);
    }

    @Override
    public Address removeAddress(String lastName) throws AddressBookDaoException {
        loadAddress();
        Address toRemove = this.addressBook.remove(lastName);
        writeAddress();
        return toRemove;
    }

    @Override
    public int addressBookSize() {
        return this.addressBook.size();
    }

    public Address unmarshallAddress(String addressAsText){
        // addressAsText is expecting a line read in from our file.
        // addressAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // Jefferson::Agyekum::100 Campus Circle::Owings Mills::MD::21117
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in addressTokens.
        // Which should look like this:
        // ______________________________________________________
        // |    |       |                 |            |  |     |
        // |Jeff|Agyekum|100 Campus Circle|Owings Mills|MD|21117|
        // |    |       |                 |            |  |     |
        // ------------------------------------------------------
        //  [0]  [1]             [2]         [3]       [4]  [5]


        String[] addressTokens = addressAsText.split(DELIMITER);
        String fName = addressTokens[0];
        String lName = addressTokens[1];
        String street = addressTokens[2];
        String city = addressTokens[3];
        String state = addressTokens[4];
        String zipCode = addressTokens[5];

        Address address = new Address(lName);
        address.setFirstName(fName);
        address.setStreetAddress(street);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);

        return address;
    }

    private void loadAddress() throws AddressBookDaoException {
        Scanner scanner;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new AddressBookDaoException(
                    "-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // current Address holds the most recent address unmarshalled
        Address currentAddress;
        // Go through ROSTER_FILE line by line, decoding each line into a
        // Address object by calling the unmarshallAddress method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentAddress = unmarshallAddress(currentLine);

            // We are going to use the Last name as the map key for our Address object.
            // Put currentAddress into the map using Last as the key
            addressBook.put(currentAddress.getLastName(), currentAddress);
        }
        // close scanner
        scanner.close();
    }

    private String marshallAddress(Address address){
        // We need to turn an Address object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // Jefferson::Agyekum::100 Campus Circle::Owings Mills::MD::21117

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer.

        // Start with the first name, since that's supposed to be first.
        String addressAsText = address.getFirstName() + DELIMITER;

        // add the rest of the properties in the correct order:

        // LastName
        addressAsText += address.getLastName() + DELIMITER;

        // Street information
        addressAsText += address.getStreetAddress() + DELIMITER;

        // City information
        addressAsText += address.getCity() + DELIMITER;


        // State information
        addressAsText += address.getState() + DELIMITER;

        // ZipCode information
        addressAsText += address.getZipCode();

        // We have now turned an Address to text! Return it!
        return addressAsText;
    }

    private void writeAddress() throws AddressBookDaoException{
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));

        } catch (IOException e){
            throw new AddressBookDaoException("Could not save Address data." + e);
        }

        String addressAsText;
        List<Address> addresses = this.getAllAddresses();
        for (Address currentAddress : addresses){
            // turn an Address into a string =
            addressAsText = marshallAddress(currentAddress);

            // write the Address to the file.
            out.println(addressAsText);

            // force the Address object to the file
            out.flush();
        }

        out.close();

        }
}

