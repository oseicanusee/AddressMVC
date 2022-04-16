package Address.addressbookdao;


import Address.DTO.Address;

import java.util.List;

public interface AddressBookDao {

    /**
     *  Adds the given address to the Address Book and associates it with the last name.
     * @param lastName which is used to identify the address.
     * @param address the address object being stored.
     * @return
     */
    Address addAddress(String lastName, Address address) throws AddressBookDaoException;


    /**
     * returns a list of all the Addresses in the address book.
     */
    List<Address> getAllAddresses () throws AddressBookDaoException;


    /**
     * Gets an address and returns it using the String provided in the parameter.
     * @param lastName
     * @return
     */
    Address getAddress(String lastName) throws AddressBookDaoException;


    /**
     * Uses the last name provided in the parameter to get the Address, remove it and then return it.
     * @param lastName
     * @return
     */
    Address removeAddress(String lastName) throws AddressBookDaoException;


    /**
     *
     * @return the number of address in the address book.
     */
    int addressBookSize();

}
