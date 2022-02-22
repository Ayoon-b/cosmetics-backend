package com.teamtbd.cosmetics.product.service;

import com.teamtbd.cosmetics.domain.Category;
import com.teamtbd.cosmetics.domain.Site;
import com.teamtbd.cosmetics.product.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductServiceTest {

	@Autowired
	ProductService productService;

	@Test
	void getProductFromJsonObject() throws ParseException {
		JSONObject jsonObject = (JSONObject) new JSONParser().parse("{\n" +
																	"  \"id\": \"9868e621-63da-4210-999f-f6910f206cd7\",\n" +
																	"  \"name\": \"바이오더마 센시비오 토너 250ml\",\n" +
																	"  \"price\": \"27000\",\n" +
																	"  \"brand\": \"바이오더마\",\n" +
																	"  \"category\": \"1\",\n" +
																	"  \"site\": 1,\n" +
																	"  \"site_id\": \"A000000002839\",\n" +
																	"  \"site_category\": \"100000100010008\",\n" +
																	"  \"link\": \"https://www.oliveyoung.co.kr/store/goods/getGoodsDetail.do?goodsNo=A000000002839&dispCatNo=100000100010008&trackingCd&curation&egcode&rccode&egrankcode\",\n" +
																	"  \"image_url\": \"https://image.oliveyoung.co.kr/uploads/images/goods/400/10/0000/0000/A00000000283907ko.jpg?l=ko\",\n" +
																	"  \"volume\": \"250ml\",\n" +
																	"  \"expiration_date\": \"제조일로부터 3년 이내의 상품을 순차적으로 발송/ 개봉 후 6개월 사용 권장\",\n" +
																	"  \"usage\": \"세안 후 화장솜에 적당량을 덜어 부드럽게 패딩해줍니다. \",\n" +
																	"  \"origin\": \"프랑스\",\n" +
																	"  \"ingredients\": \"정제수, 글리세린, 자일리톨, 폴리솔베이트20, 피이지-40하이드로제네이티드캐스터오일, 다이소듐이디티에이, 알란토인, 세트리모늄브로마이드, 프로필렌글라이콜, 오이열매추출물, 프룩토올리고사카라이드, 만니톨, 소듐하이드록사이드, 시트릭애씨드, 람노오스 ※제공된 성분은 동일 제품이라도 경우에 따라 변경될 수 있습니다. 최신정보는 제품 포장의 성분을 참고하시거나 본사 고객센터(02-523-7676)으로 연락 부탁 드립니다\",\n" +
																	"  \"caution\": \"1) 화장품 사용 시 또는 사용 후 직사광선에 의하여 사용부위가 붉은 반점, 부어오름 또는 가려움증 등의 이상 증상이나 부작용이 있는 경우 전문의 등과 상담할 것 2) 상처가 있는 부위 등에는 사용을 자제할 것 3) 보관 및 취급 시의 주의사항 가. 어린이의 손이 닿지 않는 곳에 보관할 것 나. 직사광선을 피해서 보관할 것  \"\n" +
																	"}");

		Product product = productService.getProductFromJsonObject(jsonObject);
		assertEquals("바이오더마 센시비오 토너 250ml", product.getName());
	}

	@Test
	void getProductsFromJsonArrayTest() throws ParseException {
		//language=JSON
		JSONArray jsonArray = (JSONArray) new JSONParser().parse("[\n" +
																 "  {\n" +
																 "    \"id\": \"77b25108-0cbc-4a81-acb4-00c606bcd894\",\n" +
																 "    \"name\": \"DMS 플루티오덤 플러스 50ml\",\n" +
																 "    \"price\": \"77000\",\n" +
																 "    \"brand\": \"DMS\",\n" +
																 "    \"image_url\": \"https://static.mynunc.com/ext/images/goods/top/22/01/14/05/BO00112836/0000256875.png\",\n" +
																 "    \"category\": 1,\n" +
																 "    \"site\": 5,\n" +
																 "    \"site_id\": \"BO00112836\",\n" +
																 "    \"link\": \"https://www.mynunc.com/product/goods/view-goods?goodsId=BO00112836&dspCateId=\",\n" +
																 "    \"site_category\": \"20000130\",\n" +
																 "    \"volume\": \"50ML\",\n" +
																 "    \"expiration_date\": \"별도 표기\",\n" +
																 "    \"usage\": \"상세페이지 참조\",\n" +
																 "    \"origin\": \"독일\",\n" +
																 "    \"ingredients\": \"상세페이지 참조\",\n" +
																 "    \"caution\": \"1. 화장품을 사용하여 다음과 같은 이상이 있는 경우에는 사용을 중지하여야 하며, 계속 사용 하면 증상이 악화되므로 피부과 전문의 등에게 상담할 것 1) 사용 중 붉은 반점, 부어오름, 가려움증, 자극 등의 이상이 있는 경우 2) 적용 부위가 직사 광선에 의하여 위와 같은 이상이 있는 경우 2. 상처가 있는 부위, 습진 및 피부염 등의 이상이 있는 부위에는 사용을 하지 말 것 3. 보관 및 취급 시의 주의사항 1) 사용 후에는 반드시 마개를 닫아둘 것 2) 유아ㆍ소아의 손이 닿지 않는 곳에 보관할 것 3) 고온 또는 저온의 장소 및 직사광선이 닿는 곳에는 보관하지 말 것 \"\n" +
																 "  },\n" +
																 "  {\n" +
																 "    \"id\": \"176d5f54-82dc-41a0-a976-bd3c2ec602b0\",\n" +
																 "    \"name\": \"DMS 더모 에센셜 리치엠 로션 200ml\",\n" +
																 "    \"price\": \"34000\",\n" +
																 "    \"brand\": \"DMS\",\n" +
																 "    \"image_url\": \"https://static.mynunc.com/ext/images/goods/top/22/01/14/05/BO00112836/0000256875.png\",\n" +
																 "    \"category\": 1,\n" +
																 "    \"site\": 5,\n" +
																 "    \"site_id\": \"BO00112831\",\n" +
																 "    \"link\": \"https://www.mynunc.com/product/goods/view-goods?goodsId=BO00112831&dspCateId=\",\n" +
																 "    \"site_category\": \"20000130\",\n" +
																 "    \"volume\": \"200ML\",\n" +
																 "    \"expiration_date\": \"별도 표기\",\n" +
																 "    \"usage\": \"상세페이지 참조\",\n" +
																 "    \"origin\": \"한국\",\n" +
																 "    \"ingredients\": \"상세페이지 참조\",\n" +
																 "    \"caution\": \"1. 화장품을 사용하여 다음과 같은 이상이 있는 경우에는 사용을 중지하여야 하며, 계속 사용 하면 증상이 악화되므로 피부과 전문의 등에게 상담할 것 1) 사용 중 붉은 반점, 부어오름, 가려움증, 자극 등의 이상이 있는 경우 2) 적용 부위가 직사 광선에 의하여 위와 같은 이상이 있는 경우 2. 상처가 있는 부위, 습진 및 피부염 등의 이상이 있는 부위에는 사용을 하지 말 것 3. 보관 및 취급 시의 주의사항 1) 사용 후에는 반드시 마개를 닫아둘 것 2) 유아ㆍ소아의 손이 닿지 않는 곳에 보관할 것 3) 고온 또는 저온의 장소 및 직사광선이 닿는 곳에는 보관하지 말 것\"\n" +
																 "  },\n" +
																 "  {\n" +
																 "    \"id\": \"f3ab4a7d-c0db-450d-a458-cdbb4c7edd44\",\n" +
																 "    \"name\": \"쎄라덤 쿠컴버 토닝 로션\",\n" +
																 "    \"price\": \"29000\",\n" +
																 "    \"brand\": \"쎄라덤\",\n" +
																 "    \"image_url\": \"https://static.mynunc.com/ext/images/goods/top/22/01/14/05/BO00112836/0000256875.png\",\n" +
																 "    \"category\": 1,\n" +
																 "    \"site\": 5,\n" +
																 "    \"site_id\": \"BO00112805\",\n" +
																 "    \"link\": \"https://www.mynunc.com/product/goods/view-goods?goodsId=BO00112805&dspCateId=\",\n" +
																 "    \"site_category\": \"20000130\",\n" +
																 "    \"volume\": \"140\",\n" +
																 "    \"expiration_date\": \"개봉후 12개월 이내 사용\",\n" +
																 "    \"usage\": \"적당량을 덜어 피부에 골고루 펴 발라 줍니다.\",\n" +
																 "    \"origin\": \"한국\",\n" +
																 "    \"ingredients\": \"상세페이지 참조\",\n" +
																 "    \"caution\": \"1) 화장품 사용 시 또는 사용 후 직사광선에 의하여 사용부위가 붉은 반점, 부어오름 또는 가려움증 등의 이상 증상이나 부작용이 있는 경우 전문의 등과 상담할 것 2) 상처가 있는 부위 등에는 사용을 자제할 것 3) 보관 및 취급 시의 주의사항 가) 어린이의 손이 닿지 않는 곳에 보관할 것 나) 직사광선을 피해서 보관할 것 *만 3세 이하 어린이에게는 사용하지말 것\\\"\"\n" +
																 "  },\n" +
																 "  {\n" +
																 "    \"id\": \"a0bf7578-6076-43a0-a5a3-1eb6018f5450\",\n" +
																 "    \"name\": \"라포티셀 A.C. 데일리 카밍 모이스춰라이져 50ml\",\n" +
																 "    \"price\": \"20700\",\n" +
																 "    \"brand\": \"라포티셀\",\n" +
																 "    \"image_url\": \"https://static.mynunc.com/ext/images/goods/top/22/01/14/05/BO00112836/0000256875.png\",\n" +
																 "    \"category\": 1,\n" +
																 "    \"site\": 5,\n" +
																 "    \"site_id\": \"BO00112595\",\n" +
																 "    \"link\": \"https://www.mynunc.com/product/goods/view-goods?goodsId=BO00112595&dspCateId=\",\n" +
																 "    \"site_category\": \"20000130\",\n" +
																 "    \"volume\": \"50\",\n" +
																 "    \"expiration_date\": \"개봉 후 12개월\",\n" +
																 "    \"usage\": \"상세페이지 참고\",\n" +
																 "    \"origin\": \"한국\",\n" +
																 "    \"ingredients\": \"정제수, 글리세린, 에탄올, 프로판다이올, 1,2-헥산다이올, 피이지-40하이드로제네이티드캐스터오일, 페닐트라이메티콘, 카보머, 트로메타민, 스클레로튬검, 에틸헥실글리세린, 오렌지오일, 다이소듐이디티에이, 레몬껍질오일, 베르가모트오일, 알란토인, 파스향나무잎추출물, 쇠비름추출물, 부틸렌글라이콜, 라벤더오일, 센티드제라늄꽃오일, 바질오일, 알로에베라잎추출물, 하이드롤라이즈드하이알루로닉애씨드, 세라마이드엔피, 마트리카리아꽃추출물, 살비아잎추출물, 흰버드나무껍질추출물, 티트리잎추출물, 베타-글루칸, 리모넨, 리날룰\",\n" +
																 "    \"caution\": \"1) 화장품 사용 시 또는 사용 후 직사광선에 의하여 사용부위가 붉은 반점, 부어오름 또는 가려움증 등의 이상 증상이나 부작용이 있는 경우 전문의 등과 상담할 것 2) 상처가 있는 부위 등에는 사용을 자제할 것 3) 보관 및 취급 시의 주의사항 가) 어린이의 손이 닿지 않는 곳에 보관할 것 나) 직사광선을 피해서 보관할 것 다)만 3세 이하 어린이에게는 사용하지 말것\"\n" +
																 "  },\n" +
																 "  {\n" +
																 "    \"id\": \"d1cf4dbd-1737-4884-b386-88a39825cc60\",\n" +
																 "    \"name\": \"라포티셀 오일 컷 클레이 로션 50ml\",\n" +
																 "    \"price\": \"18200\",\n" +
																 "    \"brand\": \"라포티셀\",\n" +
																 "    \"image_url\": \"https://static.mynunc.com/ext/images/goods/top/22/01/14/05/BO00112836/0000256875.png\",\n" +
																 "    \"category\": 1,\n" +
																 "    \"site\": 5,\n" +
																 "    \"site_id\": \"BO00112560\",\n" +
																 "    \"link\": \"https://www.mynunc.com/product/goods/view-goods?goodsId=BO00112560&dspCateId=\",\n" +
																 "    \"site_category\": \"20000130\",\n" +
																 "    \"volume\": \"50\",\n" +
																 "    \"expiration_date\": \"개봉 후 12개월\",\n" +
																 "    \"usage\": \"상세페이지 참고\",\n" +
																 "    \"origin\": \"한국\",\n" +
																 "    \"ingredients\": \"정제수, 프로판다이올, 글리세린, 세테아릴올리베이트," +
                                                                 " 솔비탄올리베이트, 1,2-헥산다이올, 사이클로펜타실록세인, 세테아릴알코올, " +
                                                                 "세틸에틸헥사노에이트, 글리세릴스테아레이트, 실리카, 부틸렌글라이콜, 카올린(3,000ppm)" +
                                                                 ", 카보머, 트로메타민, 에틸헥실글리세린, 녹차추출물, 스페인감초뿌리추출물, " +
                                                                 "에우파토리아짚신나물추출물, 다이소듐이디티에이, 하이드롤라이즈드호호바에스터, 소엽잎추출물, " +
                                                                 "병풀추출물, 약모밀추출물, 멕시칸치아씨추출물, 소듐하이알루로네이트, 베타-글루칸, " +
                                                                 "바이오사카라이드검-1, 흰무늬엉겅퀴씨추출물, 닥나무뿌리추출물, 프로폴리스추출물, " +
                                                                 "눈빛승마추출물, 흰버드나무껍질추출물, 달맞이꽃추출물\",\n" +
																 "    \"caution\": \"1) 화장품 사용 시 또는 사용 후 직사광선에 의하여 사용부위가 붉은 반점, 부어오름 또는 가려움증 등의 이상 증상이나 부작용이 있는 경우 전문의 등과 상담할 것 2) 상처가 있는 부위 등에는 사용을 자제할 것 3) 보관 및 취급 시의 주의사항 가) 어린이의 손이 닿지 않는 곳에 보관할 것 나) 직사광선을 피해서 보관할 것\"\n" +
																 "  }\n" +
																 "]");

		List<Product> products = productService.getProductsFromJsonArray(jsonArray);
		System.out.println("products = " + products);
	}

    @Test
    void saveProductsFromPathsTest() throws IOException, ParseException{
        productService.saveProductsFromPaths(productService.getFileList("src/main/resources/cosmeticData"));
    }

    @Test
    void getFileListTest() throws IOException {
        System.out.println("productService = " + productService.getFileList("src/main/resources/cosmeticData"));
    }

    @Test
    void getProductsByCategoryTest() {
        System.out.println(productService.getProductsByCategory(Category.EYE_LINER));
    }

    @Test
    void getProductsBySiteTest() {
        System.out.println(productService.getProductsBySite(Site.INNISFREE));
    }

    @Test
    void getProductsByNameContainsTest() {
        System.out.println(productService.getProductsByNameContains("미샤"));
    }

    @Test
    void getProductsByCategoryWithPageRequestTest() {
        System.out.println(productService.getProductsByCategory(Category.EYE_LINER, PageRequest.of(0,24)));
    }

    @Test
    void getProductsByNameContainsWithPageRequestTest() {
        System.out.println(productService.getProductsByNameContains("미샤", PageRequest.of(0,24)));
    }

    @Test
    void getProductsBySiteWithPageRequestTest() {
        System.out.println(productService.getProductsBySite(Site.NATURE_REPUBLIC, PageRequest.of(0,24)));
    }

    @Test
    void getAllBrandsTest() {
        System.out.println(productService.getAllBrands());
    }

    @Test
    void getProductsByBrandTest() {
        System.out.println(productService.getProductsByBrand("아이오페"));
    }

    @Test
    void getProductsByPriceRangeTest() {
        System.out.println(productService.getProductsByPriceRange(0,10000));
    }

    @Test
    void getProductsByIngredientsContainsTest() {
        System.out.println(productService.getProductsByIngredientsContains("오일"));
    }

    @Test
    void getProductsByPriceAscTest() {
        System.out.println(productService.getProductsByPriceAsc());
    }

    @Test
    void getProductsByPriceDescTest() {
        System.out.println(productService.getProductsByPriceDesc());
    }

	@Test
	void getProductTest() {
		System.out.println(productService.getProduct("0de49c93-353e-4d37-ba28-42738a0d0943"));
	}
}
