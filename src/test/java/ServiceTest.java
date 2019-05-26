//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alex Ryzhkov
 */
//@ExtendWith(MockitoExtension.class)
//public class ServiceTest {
//	private Merchant merchant;
//	@Mock
//	private MerchantRepository merchantRepository;
//	@InjectMocks
//	private MerchantService serviceToTest;
//
//	@BeforeEach
//	public void invokeBeforeEachTestMethod() {
//		merchant = new Merchant();
//		merchant.setAccount("123456");
//		merchant.setId(233);
//	}
//
//	@Test
//	@DisplayName("Should show the good weather scenario")
//	public void testGetMerchantById() {
//		when(merchantRepository.findById(123)).thenReturn(merchant);

//		Merchant merchantFoundById = serviceToTest.findById(123);
		//single assertion
//		assertEquals(merchant, merchantFoundById,
//				"The result of the execution of serviceToTest.findById() method does not return an expected object");

		//multiple assertions at once
//		assertAll(() -> assertEquals("123456", merchantFoundById.getAccount()),
//				() -> assertEquals(233, merchantFoundById.getId()));
//	}

//}
