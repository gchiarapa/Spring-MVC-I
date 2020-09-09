package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamento;

@RequestMapping("/pagamento")
@Controller
public class PagamentoController {
	
	@Autowired
	private RestTemplate restTempalte;
	
	@Autowired
	private CarrinhoCompras carrinho;
	
	@RequestMapping(value="/finalizar", method = RequestMethod.POST)
	public Callable<ModelAndView> finalizar(RedirectAttributes model) {
		return () -> {
			
			String uri = "http://book-payment.herokuapp.com/payment";
			
			try {
				String responsePayment = restTempalte.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);
				System.out.println(responsePayment);
				model.addFlashAttribute("sucesso",responsePayment);
				return new ModelAndView("redirect:/produtos");
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Valor maior que o permitido");
				model.addFlashAttribute("falha","Valor maior que o permitido");
				return new ModelAndView("redirect:/produtos");
			}
			
		};
	}

}
