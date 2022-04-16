package Address;

import Address.addressbookController.AddressBookController;
import Address.addressbookdao.AddressBookDao;
import Address.addressbookdao.AddressBookDaoImpl;
import Address.ui.AddressBookView;
import Address.ui.UserIO;
import Address.ui.UserIoConsoleImpl;

public class App {
    public static void main(String[] args) {
        UserIO io = new UserIoConsoleImpl();
        AddressBookDao dao = new AddressBookDaoImpl();
        AddressBookView view = new AddressBookView(io);
        AddressBookController controller = new AddressBookController(dao, view);
        controller.run();
    }
}
