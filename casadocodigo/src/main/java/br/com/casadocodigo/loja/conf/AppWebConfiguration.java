package br.com.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.CarrinhoCompras;

@EnableWebMvc
//configura a classe do Controller
@ComponentScan(basePackageClasses = {HomeController.class, ProdutoDAO.class, FileSaver.class, CarrinhoCompras.class})
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	
//TODO anotação Bean, informa ao spring que é uma classe gerenciada por ele
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		//TODO configura o caminho das páginas
		resolver.setPrefix("/WEB-INF/views/");
		//TODO configura a extensão das páginas
		resolver.setSuffix(".jsp");
		
		//TODO disponibiliza os Beans como atributo na jsp
		resolver.setExposedContextBeanNames("carrinhoCompras");
		return resolver;
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messagesSource = new ReloadableResourceBundleMessageSource();
		messagesSource.setBasename("/WEB-INF/messages");
		messagesSource.setDefaultEncoding("UTF-8");
		messagesSource.setCacheSeconds(1);
		return messagesSource;
	}
	
	@Bean
	//TODO Configura o formato da data padrão
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
		formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		formatterRegistrar.registerFormatters(conversionService);
		
		return conversionService;
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
	@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			// TODO Habilita o CSS
			configurer.enable();
		}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
