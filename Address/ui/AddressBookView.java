package Address.ui;

import Address.DTO.Address;

import java.util.List;

public class AddressBookView {
    private UserIO io;

    public AddressBookView(UserIO io){
        this.io = io;
    }

    public int printMenuSelection(){
        io.print("Initial menu: ");
        io.print("\t\t1. Add Address");
        io.print("\t\t2. Delete Address");
        io.print("\t\t3. Find Address");
        io.print("\t\t4. List Address Count");
        io.print("\t\t5. List All Address");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public Address getAddressInformation(){
        String firstName = io.readString("Please Enter First Name: ");
        String lastName = io.readString("Please Enter Last Name: ");
        String street = io.readString("Please Enter Street Name: ");
        String city = io.readString("Please Enter City Name: ");
        String state = io.readString("Please Enter State Name: ");
        String zipCode = io.readString("Please Enter Zip Code: ");
        Address address = new Address(lastName);
        address.setFirstName(firstName);
        address.setStreetAddress(street);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);
        return address;
    }

    public String getRemoveAddressInformation(){
        String lastName = io.readString("Please enter last name of address to delete: ");
        return lastName;
    }

    public String getFindAddressInformation(){
        return io.readString("Please enter last name of address to find");
    }

    public void displayAddressBanner() {
        io.print("=== Add Address Menu ===");
    }

    public void displayFindAddressBanner(){
        io.print("=== Find Address Menu ====");
    }

    public void displayAddressAddedSuccessBanner(){
        io.readString("Address successfully added. Please hit enter to continue");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Addresses ===");
    }

    public void displayDisplayAddressBanner(){
        io.print("=== Display Address ===");
    }

    public void displayExitBanner(){
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner(){
        io.print("Unknown Command!!!");
    }

    public void displayRemoveAddressBanner(){
        io.print("=== Delete Address Menu ===");
    }

    public void displayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayRemoveResult(Address removedAddress){
        if (removedAddress != null) {
            io.print("Really Delete?");
            io.readString("Address Deleted. Press 1 to go to Main Menu.");
        } else {
            io.print("No such Address");
        }
    }

    public void displayMenu(){
        io.readString("Press 1 to go to Main Menu.");
    }

    public void printAddress(Address address){
        io.print(address.getFirstName() + " " + address.getLastName());
        io.print(address.getStreetAddress());
        io.print(address.getCity() + ", " + address.getState() + " " + address.getZipCode());
        System.out.println();
    }

    public void addressCountDisplayBanner(){
        io.print("List Address Count Menu: ");
    }

    public void printAddressBookSize(int size){
        if (size > 1) {
            io.print("There are " + size + " addresses in the book.");
        } else {
            io.print("There is " + size + " address in the book");
        }
            io.readString("Press 1 to go to Main Menu");
    }

    public void printAllAddress(List<Address> addressList){
        for (Address address : addressList){
            printAddress(address);
            System.out.println();
        }
    }

    public void displayAllAddressBanner(){
        io.print("List Addresses Menu: ");
    }

}
