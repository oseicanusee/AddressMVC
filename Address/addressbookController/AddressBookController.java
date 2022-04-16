package Address.addressbookController;

import Address.DTO.Address;
import Address.addressbookdao.AddressBookDao;
import Address.addressbookdao.AddressBookDaoException;
import Address.ui.AddressBookView;

import java.util.List;

public class AddressBookController {
    private AddressBookDao dao;
    private AddressBookView view;

    public AddressBookController(AddressBookDao dao, AddressBookView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1 : addAddress();
                            break;
                    case 2: deleteAddress();
                            break;
                    case 3: findAddress();
                            break;
                    case 4: numberOfAddresses();
                            break;
                    case 5: listAllAddresses();
                            break;
                    default: unknownCommand();
                            break;
                }
                exitMenu();
            }

        } catch (AddressBookDaoException e) {
                view.displayErrorMessage(e.getMessage());
        }
    }

    public int getMenuSelection(){
        return view.printMenuSelection();
    }

    public void addAddress() throws AddressBookDaoException {
        view.displayAddressBanner();
        Address address = view.getAddressInformation();
        dao.addAddress(address.getLastName(), address);
        view.displayAddressAddedSuccessBanner();
    }

    public void deleteAddress() throws AddressBookDaoException {
        view.displayRemoveAddressBanner();
        String lastName = view.getRemoveAddressInformation();
        System.out.println();
        Address toRemove = dao.getAddress(lastName);
        view.printAddress(toRemove);
        view.displayRemoveResult(toRemove);
        Address removed = dao.removeAddress(toRemove.getLastName());
    }

    public void findAddress() throws AddressBookDaoException {
        view.displayFindAddressBanner();
        String lastName = view.getFindAddressInformation();
        System.out.println();
        Address findAddress = dao.getAddress(lastName);
        view.printAddress(findAddress);
        view.displayMenu();
    }

    public void numberOfAddresses(){
        view.addressCountDisplayBanner();
        int number = dao.addressBookSize();
        view.printAddressBookSize(number);
    }

    public void listAllAddresses() throws AddressBookDaoException {
        view.displayAllAddressBanner();
        List<Address> addresses = dao.getAllAddresses();
        view.printAllAddress(addresses);
        view.displayMenu();
    }

    public void unknownCommand(){
        view.displayUnknownCommandBanner();
    }

    public void exitMenu(){
        view.displayExitBanner();
    }


    }

