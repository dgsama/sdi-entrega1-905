package com.uniovi;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;
import com.uniovi.services.OffersService;
import com.uniovi.services.RolesService;
import com.uniovi.services.UsersService;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_OfferView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyWallapopTest_905 {

	@Autowired
	private UsersService usersService;
	@Autowired
	private RolesService rolesService;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private OffersService offersService;

	static String PathFirefox64 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver022 = "D:\\David\\Documents\\SDI 2019\\Material\\SeleniumUtilsTests\\geckodriver024win64.exe";
	static WebDriver driver = getDriver(PathFirefox64, Geckdriver022);
	static String Local_URL = "http://localhost:8090";
	static String Remote_URL = "http://localhost:8090";
	static String URL ="";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		initdb();
		URL = Local_URL;
		driver.navigate().to(Local_URL);
		
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	public void initdb() {
		// Borramos todas las entidades.
		usersRepository.deleteAll();

		/** ADMINISTRADOR **/
		User admin = new User("admin@email.com", "Administrador", "Sistema");
		admin.setPassword("admin");
		admin.setMoney(999.0);
		admin.setRole(rolesService.getRoles()[1]);

		/** USUARIOS **/
		User user1 = new User("andrei@mail.com", "Andrei", "Manu");
		user1.setPassword("123456");
		user1.setMoney(100.0);
		user1.setRole(rolesService.getRoles()[0]);
		User user2 = new User("rosario@mail.com", "Rosario", "Palacios");
		user2.setPassword("123456");
		user2.setMoney(100.0);
		user2.setRole(rolesService.getRoles()[0]);
		User user3 = new User("david@mail.com", "David", "Garcia");
		user3.setPassword("123456");
		user3.setMoney(100.0);
		user3.setRole(rolesService.getRoles()[0]);
		User user4 = new User("cova@mail.com", "Cova", "Arroyo");
		user4.setPassword("123456");
		user4.setMoney(100.0);
		user4.setRole(rolesService.getRoles()[0]);
		User user5 = new User("raul@mail.com", "Raul", "González Blanco");
		user5.setPassword("123456");
		user5.setMoney(100.0);
		user5.setRole(rolesService.getRoles()[0]);

		/** AÑADIR USUARIOS **/
		usersService.addUser(admin);
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);

		/** OFERTAS USER 1 **/
		Offer offer1 = new Offer("Ford focus tdi",
				"Coche de segunda mano con 200k kilometros, diesel, año 2007 color rojo pasión. Se regalan neumaticos de invierno",
				6000.0);
		offer1.setDate(new Date());
		offer1.setCreator(user1);

		Offer offer2 = new Offer("Renault clio tdi",
				"Coche de segunda mano con 200k kilometros, diesel, año 2007 color rojo pasión. Se regalan neumaticos de invierno",
				6000.0);
		offer2.setDate(new Date());
		offer2.setCreator(user1);
		offer2.setBuyer(user2);
		offer2.setSold(true);

		Offer offer3 = new Offer("Sara picaso tdi",
				"Coche de segunda mano con 200k kilometros, diesel, año 2007 color rojo pasión. Se regalan neumaticos de invierno",
				10.0);
		offer3.setDate(new Date());
		offer3.setCreator(user1);
		offer3.setBuyer(user2);
		offer3.setSold(true);

		/** OFERTAS USER 2 **/
		Offer offer4 = new Offer("Kit de supervivencia", "Inmejorable kit para la supervivencia en ambientes hostiles.",
				80.0);
		offer4.setDate(new Date());
		offer4.setCreator(user2);

		Offer offer5 = new Offer("Mochila Kargo", "Mochila del ejercito sovietico probada en ambientes siberianos.",
				1000.0);
		offer5.setDate(new Date());
		offer5.setCreator(user2);
		offer5.setBuyer(user3);
		offer5.setSold(true);

		Offer offer6 = new Offer("Pistola de bengalas modelo k67",
				"Pistola de emergencia apta para todo tipo de ambientes, luz naranja.", 12.0);
		offer6.setDate(new Date());
		offer6.setCreator(user2);
		offer6.setBuyer(user3);
		offer6.setSold(true);

		/** OFERTAS USER 3 **/
		Offer offer7 = new Offer("Tekken 6", "Juego ps3 precintado edición coleccionista.", 35.0);
		offer7.setDate(new Date());
		offer7.setCreator(user3);

		Offer offer8 = new Offer("God of war 4", "La mayor genialidad de sony en 2018.", 69.0);
		offer8.setDate(new Date());
		offer8.setCreator(user3);
		offer8.setBuyer(user4);
		offer8.setSold(true);

		Offer offer9 = new Offer("Fifa 19", "DjMario sube el camino", 45.0);
		offer9.setDate(new Date());
		offer9.setCreator(user3);
		offer9.setBuyer(user4);
		offer9.setSold(true);

		/** OFERTAS USER 4 **/
		Offer offer10 = new Offer("Husky ojos azules y verdes", "Adopta perros, no compres. Precio real gratis.",
				180.0);
		offer10.setDate(new Date());
		offer10.setCreator(user4);

		Offer offer11 = new Offer("Alaskan Malamute hembra", "Adopta perros, no compres. Precio real gratis.", 200.0);
		offer11.setDate(new Date());
		offer11.setCreator(user4);
		offer11.setBuyer(user5);
		offer11.setSold(true);

		Offer offer12 = new Offer("Gato egipcio, 4 meses", "Adopta gatos, no compres. Precio real gratis.", 10.0);
		offer12.setDate(new Date());
		offer12.setCreator(user4);
		offer12.setBuyer(user5);
		offer12.setSold(true);

		/** OFERTAS USER 5 **/
		Offer offer13 = new Offer("El nombre del viento",
				"Crónica del asesino de reyes: primer día es una novela de fantasía épica, perteneciente a la serie Crónica del Asesino de Reyes, escrita por Patrick Rothfus.",
				100.0);
		offer13.setDate(new Date());
		offer13.setCreator(user5);

		Offer offer14 = new Offer("El señor de los anillos versión original", "Poco más hay que decir", 27000.0);
		offer14.setDate(new Date());
		offer14.setCreator(user5);
		offer14.setBuyer(user1);
		offer14.setSold(true);

		Offer offer15 = new Offer("Juego de tronos", "Si eres sensible a la muerte no lo compres.", 19.0);
		offer15.setDate(new Date());
		offer15.setCreator(user5);
		offer15.setBuyer(user1);
		offer15.setSold(true);

		/** AÑADIR TODAS LAS OFERTAS **/
		offersService.addOffer(offer1);
		offersService.addOffer(offer2);
		offersService.addOffer(offer3);
		offersService.addOffer(offer4);
		offersService.addOffer(offer5);
		offersService.addOffer(offer6);
		offersService.addOffer(offer7);
		offersService.addOffer(offer8);
		offersService.addOffer(offer9);
		offersService.addOffer(offer10);
		offersService.addOffer(offer11);
		offersService.addOffer(offer12);
		offersService.addOffer(offer13);
		offersService.addOffer(offer14);
		offersService.addOffer(offer15);

	}

	/** TESTS **/

	// [Prueba1] Registro de Usuario con datos válidos.
	@Test
	public void P01() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "juan@mail.com", "Josefo", "Perez", "123456", "123456");
		PO_View.checkElement(driver, "text", "Bienvenidos a la página principal");
	}

	// [Prueba2] Registro de Usuario con datos inválidos (email vacío, nombre vacío,
	// apellidos vacíos).
	@Test
	public void P02() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "", "", "", "123456", "123456");
		PO_View.checkElement(driver, "text", "Registráte como usuario");
	}

	// [Prueba3] Registro de Usuario con datos inválidos (repetición de contraseña
	// inválida).
	@Test
	public void P03() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "juan@mail.com", "Josefo", "Perez", "1234567", "123456");
		PO_View.checkElement(driver, "text", "Las contraseñas no coinciden");
	}

	// [Prueba4] Registro de Usuario con datos inválidos (email existente).
	@Test
	public void P04() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "andrei@mail.com", "Josefo", "Perez", "123456", "123456");
		PO_View.checkElement(driver, "text", "El email ya existe.");
	}

	// [Prueba5] Inicio de sesión con datos válidos (administrador).
	@Test
	public void P05() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// // COmprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Gestión de usuarios");
	}

	// [Prueba6] Inicio de sesión con datos válidos (usuario estándar).
	@Test
	public void P06() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "andrei@mail.com", "123456");
		// // COmprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Bienvenido");
	}

	// [Prueba7] Inicio de sesión con datos inválidos (usuario estándar, campo email
	// y contraseña vacíos).
	@Test
	public void P07() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "", "");
		// // COmprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	// [Prueba8] Inicio de sesión con datos válidos (usuario estándar, email
	// existente, pero contraseña incorrecta).
	@Test
	public void P08() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "andrei@mail.com", "123456444");
		// // COmprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Email o contraseña inválido.");
	}

	// [Prueba9] Inicio de sesión con datos inválidos (usuario estándar, email no
	// existente en la aplicación).
	@Test
	public void P09() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "notandrei@mail.com", "123456");
		// // COmprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Email o contraseña inválido.");
	}

	// [Prueba10] Hacer click en la opción de salir de sesión y comprobar que se
	// redirige a la página de inicio de sesión (Login)
	@Test
	public void P10() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "andrei@mail.com", "123456");
		// COmprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Bienvenido");
		// Ahora nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "text", "Email");
	}

	// [Prueba11] Comprobar que el botón cerrar sesión no está visible si el usuario
	// no está autenticado.
	@Test
	public void P11() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		ExpectedConditions.invisibilityOfElementLocated(By.id("logout"));
	}

	// [Prueba12] Mostrar el listado de usuarios y comprobar que se muestran todos
	// los que existen en el sistema.
	@Test
	public void P12() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.checkElement(driver, "text", "Bienvenido");
		driver.navigate().to(URL + "/admin/listUsers");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		PO_PrivateView.clickOption(driver, "logout", "text", "Email");
	}

	// [Prueba13] Ir a la lista de usuarios, borrar el primer usuario de la lista,
	// comprobar que la lista se actualiza
	// y dicho usuario desaparece.

	@Test
	public void P13() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.checkElement(driver, "text", "Bienvenido");
		driver.navigate().to(URL + "/admin/listUsers");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		elementos.get(0).findElement(By.id("idsSelectedUsers")).click();
		List<WebElement> boton = PO_View.checkElement(driver, "free", "//input[contains(@id,'btnBorrar')]");
		boton.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 4);
		PO_PrivateView.clickOption(driver, "logout", "text", "Email");
	}
	// [Prueba14] Ir a la lista de usuarios, borrar el último usuario de la lista,
	// comprobar que la lista se actualiza
	// y dicho usuario desaparece.

	@Test
	public void P14() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.checkElement(driver, "text", "Bienvenido");
		driver.navigate().to(URL + "/admin/listUsers");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		elementos.get(elementos.size() - 1).findElement(By.id("idsSelectedUsers")).click();
		List<WebElement> boton = PO_View.checkElement(driver, "free", "//input[contains(@id,'btnBorrar')]");
		boton.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 4);
		PO_PrivateView.clickOption(driver, "logout", "text", "Email");
	}

	// [Prueba15] Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la
	// lista se actualiza y dichos
	// usuarios desaparecen.
	@Test
	public void P15() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.checkElement(driver, "text", "Bienvenido");
		driver.navigate().to(URL + "/admin/listUsers");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		elementos.get(0).findElement(By.id("idsSelectedUsers")).click();
		elementos.get(1).findElement(By.id("idsSelectedUsers")).click();
		elementos.get(2).findElement(By.id("idsSelectedUsers")).click();
		List<WebElement> boton = PO_View.checkElement(driver, "free", "//input[contains(@id,'btnBorrar')]");
		boton.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 2);
		PO_PrivateView.clickOption(driver, "logout", "text", "Email");
	}

	// [Prueba16] Ir al formulario de alta de oferta, rellenarla con datos válidos y
	// pulsar el botón Submit.
	// Comprobar que la oferta sale en el listado de ofertas de dicho usuario.
	@Test
	public void P16() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "andrei@mail.com", "123456");
		PO_View.checkElement(driver, "text", "Bienvenido");
		driver.navigate().to(URL + "/offer/add");
		// Rellenamos el formulario.
		PO_OfferView.fillForm(driver, "Prueba de titulo", "Esto es una descripcion", "12.0");
		PO_View.checkElement(driver, "text", "Prueba de titulo");
	}

	// [Prueba17] Ir al formulario de alta de oferta, rellenarla con datos inválidos
	// (campo título vacío) y pulsar
	// el botón Submit. Comprobar que se muestra el mensaje de campo obligatorio
	@Test
	public void P17() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "andrei@mail.com", "123456");
		PO_View.checkElement(driver, "text", "Bienvenido");
		driver.navigate().to(URL + "/offer/add");
		// Rellenamos el formulario.
		PO_OfferView.fillForm(driver, "", "Esto es una descripcion", "12.0");
		PO_View.checkElement(driver, "text", "Agregar ofertas");
	}

	// [Prueba18] Mostrar el listado de ofertas para dicho usuario y comprobar que
	// se muestran todas los que
	// existen para este usuario.
	@Test
	public void P18() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "andrei@mail.com", "123456");
		PO_View.checkElement(driver, "text", "Bienvenido");
		driver.navigate().to(URL + "/user/listOffers");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 3);
	}

	// [Prueba19] Ir a la lista de ofertas, borrar la primera oferta de la lista,
	// comprobar que la lista se actualiza y
	// que la oferta desaparece.
	@Test
	public void P19() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "andrei@mail.com", "123456");
		PO_View.checkElement(driver, "text", "Bienvenido");
		driver.navigate().to(URL + "/user/listOffers");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 3);
		List<WebElement> boton = SeleniumUtils.EsperaCargaPagina(driver, "free",
				"//tbody/tr/td/a[starts-with(@href, '/offer/delete')]", PO_View.getTimeout());
		boton.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 2);

	}

	// [Prueba20] Ir a la lista de ofertas, borrar la última oferta de la lista,
	// comprobar que la lista se actualiza y
	// que la oferta desaparece.
	@Test
	public void P20() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "andrei@mail.com", "123456");
		PO_View.checkElement(driver, "text", "Bienvenido");
		driver.navigate().to(URL + "/user/listOffers");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 3);
		List<WebElement> boton = SeleniumUtils.EsperaCargaPagina(driver, "free",
				"//tbody/tr/td/a[starts-with(@href, '/offer/delete')]", PO_View.getTimeout());
		boton.get(elementos.size() - 1).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 2);
	}

	// [Prueba21] Hacer una búsqueda con el campo vacío y comprobar que se muestra
	// la página que
	// corresponde con el listado de las ofertas existentes en el sistema
	@Test
	public void P21() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "andrei@mail.com", "123456");
		PO_View.checkElement(driver, "text", "Bienvenido");

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);

		PO_PrivateView.search(driver, "");

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 5);

	}

	// [Prueba22] Hacer una búsqueda escribiendo en el campo un texto que no exista
	// y comprobar que se
	// muestra la página que corresponde, con la lista de ofertas vacía.
	@Test
	public void P22() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "andrei@mail.com", "123456");
		PO_View.checkElement(driver, "text", "Bienvenido");

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		PO_PrivateView.search(driver, "asdfasdfas");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody", PO_View.getTimeout());
		assertTrue(elementos.size() == 1);
	}

	// [Prueba23] Sobre una búsqueda determinada (a elección de desarrollador),
	// comprar una oferta que deja
	// un saldo positivo en el contador del comprobador. Y comprobar que el contador
	// se actualiza
	// correctamente en la vista del comprador.
	@Test
	public void P23() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "andrei@mail.com", "123456");
		PO_View.checkElement(driver, "text", "Bienvenido");

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);

		PO_PrivateView.search(driver, "Kit de supervivencia");
		WebElement button = driver.findElement(By.partialLinkText("Comprar"));
		button.click();

		WebElement money = driver.findElement(By.id("money"));
		assertTrue(20.0==Double.parseDouble(money.getText().replace(" €","")));

	}

	// [Prueba24] Sobre una búsqueda determinada (a elección de desarrollador),
	// comprar una oferta que deja
	// un saldo 0 en el contador del comprobador. Y comprobar que el contador se
	// actualiza correctamente en
	// la vista del comprador.
	@Test
	public void P24() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "andrei@mail.com", "123456");
		PO_View.checkElement(driver, "text", "Bienvenido");

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);

		PO_PrivateView.search(driver, "El nombre del viento");
		WebElement button = driver.findElement(By.partialLinkText("Comprar"));
		button.click();
		
		WebElement money = driver.findElement(By.id("money"));
		assertTrue(0.0==Double.parseDouble(money.getText().replace(" €","")));
		
	}

	// [Prueba25] Sobre una búsqueda determinada (a elección de desarrollador),
	// intentar comprar una oferta
	// que esté por encima de saldo disponible del comprador. Y comprobar que se
	// muestra el mensaje de
	// saldo no suficiente
	@Test
	public void P25() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "andrei@mail.com", "123456");
		PO_View.checkElement(driver, "text", "Bienvenido");

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		PO_PrivateView.search(driver, "Husky");
		WebElement button = driver.findElement(By.partialLinkText("Comprar"));
		button.click();
		PO_View.checkElement(driver, "text", "No se dispone del credito necesario");

	}

	// [Prueba26] Ir a la opción de ofertas compradas del usuario y mostrar la
	// lista. Comprobar que aparecen
	// las ofertas que deben aparecer.
	@Test
	public void P26() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "andrei@mail.com", "123456");
		PO_View.checkElement(driver, "text", "Bienvenido");
		driver.navigate().to(URL + "/user/listBuy");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 2);

	}

	// [Prueba27] Visualizar al menos cuatro páginas en Español/Inglés/Español
	// (comprobando que algunas
	// de las etiquetas cambian al idioma correspondiente). Página
	// principal/Opciones Principales de
	// Usuario/Listado de Usuarios de Admin/Vista de alta de Oferta.
	@Test
	public void P27() {
		PO_View.checkElement(driver, "text", "Bienvenido");
		PO_HomeView.changeIdiom(driver, "English");
		PO_View.checkElement(driver, "text", "Welcome");
		
		
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Login In");
		PO_HomeView.changeIdiom(driver, "Spanish");
		PO_View.checkElement(driver, "text", "Identifícate");	
		
		PO_LoginView.fillForm(driver, "andrei@mail.com", "123456");
		PO_View.checkElement(driver, "text", "Bienvenido");
		PO_HomeView.changeIdiom(driver, "English");
		PO_View.checkElement(driver, "text", "Welcome");
		
		driver.navigate().to(URL + "/user/listOffers");
		PO_View.checkElement(driver, "text", "Created offers");
		PO_HomeView.changeIdiom(driver, "Spanish");
		PO_View.checkElement(driver, "text", "Ofertas creadas");
		
	}

	// [Prueba28] Intentar acceder sin estar autenticado a la opción de listado de
	// usuarios del administrador. Se
	// deberá volver al formulario de login.
	@Test
	public void P28() {
		driver.navigate().to(URL + "/admin/users");
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	// [Prueba29] Intentar acceder sin estar autenticado a la opción de listado de
	// ofertas propias de un usuario
	// estándar. Se deberá volver al formulario de login.
	@Test
	public void P29() {
		driver.navigate().to(URL + "/user/listOffers");
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	// [Prueba30] Estando autenticado como usuario estándar intentar acceder a la
	// opción de listado de
	// usuarios del administrador. Se deberá indicar un mensaje de acción prohibida.
	@Test
	public void P30() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "andrei@mail.com", "123456");
		driver.navigate().to(URL + "/admin/listUsers");
		ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(), 'Los usuarios que')]"));
	}
}
