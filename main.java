import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

interface Item {

}

abstract class ElectronicItem implements Item {
    protected String name;
    protected String model;
    protected String description;
    protected double price;

    public ElectronicItem(String name, String model, String description, double price) {
        this.name = name;
        this.model = model;
        this.description = description;
        this.price = price;
    }
}

class MobilePhone extends ElectronicItem {
    private final String brand;
    private final String screenSize;

    public MobilePhone(String name, String model, String description, double price, String brand, String screenSize) {
        super(name, model, description, price);
        this.brand = brand;
        this.screenSize = screenSize;
    }

    public String getBrand() {
        return brand;
    }

    public String getScreenSize() {
        return screenSize;
    }
}

class Laptop extends ElectronicItem {
    private final String processor;
    private final int ram;

    public Laptop(String name, String model, String description, double price, String processor, int ram) {
        super(name, model, description, price);
        this.processor = processor;
        this.ram = ram;
    }

    public String getProcessor() {
        return processor;
    }

    public int getRam() {
        return ram;
    }
}

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<ElectronicItem> inventory = new ArrayList<>();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n*________Menu Principal________*");
            System.out.println("1. Agregar articulo electronico");
            System.out.println("2. Modificar articulo electronico");
            System.out.println("3. Consultar inventario");
            System.out.println("4. Salir");

            try {
                int choice = getUserChoice();
                switch (choice) {
                    case 1:
                        addItem();
                        break;
                    case 2:
                        modifyItem();
                        break;
                    case 3:
                        displayInventory();
                        break;
                    case 4:
                        exit = true;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor ingrese un número válido (1-4)");
                scanner.nextLine();
            }
        }
    }

    private static int getUserChoice() {
        int choice;
        do {
            System.out.print("Ingrese su opcion: ");
            choice = scanner.nextInt();
            if (choice < 1 || choice > 4) {
                System.out.println("Opcion invalida. Por favor, ingrese un número valido (1-4)");
            }
        } while (choice < 1 || choice > 4);
        return choice;
    }

    private static void addItem() {
        System.out.println("\n*_____Agregar Artículo_____*");
        System.out.print("Tipo de articulo (1. Telefono movil, 2. Laptop): ");
        int itemType = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Modelo: ");
        String model = scanner.nextLine();
        System.out.print("Descripcion: ");
        String description = scanner.nextLine();
        System.out.print("Precio: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        switch (itemType) {
            case 1:
                System.out.print("Marca: ");
                String brand = scanner.nextLine();
                System.out.print("Tamaño de pantalla: ");
                String screenSize = scanner.nextLine();
                MobilePhone mobilePhone = new MobilePhone(name, model, description, price, brand, screenSize);
                inventory.add(mobilePhone);
                break;
            case 2:
                System.out.print("Procesador: ");
                String processor = scanner.nextLine();
                System.out.print("RAM: ");
                int ram = scanner.nextInt();
                scanner.nextLine();
                Laptop laptop = new Laptop(name, model, description, price, processor, ram);
                inventory.add(laptop);
                break;
            default:
                System.out.println("Tipo de artículo inválido.");
        }
    }

    private static void modifyItem() {
        System.out.println("\n*_____Modificar Artículo_____*");
        if (inventory.isEmpty()) {
            System.out.println("El inventario esta vacio. No hay artículos para modificar.");
            return;
        }

        System.out.print("Ingrese el numero de artículo que desea modificar: ");
        int itemNumber = scanner.nextInt();
        scanner.nextLine();

        if (itemNumber < 1 || itemNumber > inventory.size()) {
            System.out.println("Numero de artículo invalido.");
            return;
        }

        ElectronicItem itemToModify = inventory.get(itemNumber - 1);

        System.out.println("Seleccione el atributo que desea modificar:");
        System.out.println("1. Nombre");
        System.out.println("2. Modelo");
        System.out.println("3. Descripcion");
        System.out.println("4. Precio");

        int attributeChoice;
        do {
            attributeChoice = scanner.nextInt();
            scanner.nextLine();
            if (attributeChoice < 1 || attributeChoice > 4) {
                System.out.println("Opcion invalida. Por favor, ingrese un numero valido (1-4).");
            }
        } while (attributeChoice < 1 || attributeChoice > 4);

        switch (attributeChoice) {
            case 1:
                System.out.print("Nuevo nombre: ");
                String newName = scanner.nextLine();
                itemToModify.name = newName;
                break;
            case 2:
                System.out.print("Nuevo modelo: ");
                String newModel = scanner.nextLine();
                itemToModify.model = newModel;
                break;
            case 3:
                System.out.print("Nueva descripcion: ");
                String newDescription = scanner.nextLine();
                itemToModify.description = newDescription;
                break;
            case 4:
                System.out.print("Nuevo precio: ");
                double newPrice = scanner.nextDouble();
                itemToModify.price = newPrice;
                scanner.nextLine(); // Limpiar el buffer del scanner
                break;
            default:
                System.out.println("Opcion invalida.");
                return;
        }

        System.out.println("*__Artículo modificado exitosamente__*");
    }



    private static void displayInventory() {
        System.out.println("\n*_____Inventario_____*");
        int itemCount = 1;
        for (ElectronicItem item : inventory) {
            System.out.println("Articulo #" + itemCount++);
            System.out.println("Nombre: " + item.name);
            System.out.println("Modelo: " + item.model);
            System.out.println("Descripcion: " + item.description);
            System.out.println("Precio: " + item.price);
            if (item instanceof MobilePhone) {
                var mobilePhone = (MobilePhone) item;
                System.out.println("Marca: " + mobilePhone.getBrand());
                System.out.println("Tamaño de pantalla: " + mobilePhone.getScreenSize());
            } else if (item instanceof Laptop) {
                var laptop = (Laptop) item;
                System.out.println("Procesador: " + laptop.getProcessor());
                System.out.println("RAM: " + laptop.getRam());
            }
            System.out.println();
        }
    }
}
