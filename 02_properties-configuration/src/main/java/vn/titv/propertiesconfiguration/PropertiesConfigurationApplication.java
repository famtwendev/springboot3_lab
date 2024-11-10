package vn.titv.propertiesconfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class PropertiesConfigurationApplication {
	@Value("${khoahoc.ten}")
	private String ten;
	@Value("${khoahoc.huongdan}")
	private String huongdan;
	@Value("${khoahoc.website}")
	private String website;

	public static void main(String[] args) {
		SpringApplication.run(PropertiesConfigurationApplication.class, args);
	}
	@GetMapping("/")
	public String index(){
		return "TITV, Xin Chao!";
	}
	@GetMapping("/info")
	public String info()
	{
		return
				"Tên: " + ten + "<br/>" +
						"Hướng dẫn: " + huongdan +"<br/>" +
						"Website: " + website +"\n" ;
	}
}
