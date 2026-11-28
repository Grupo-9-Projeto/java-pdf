package school.sptech;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;

public class Main {

    public static void main(String[] args) {
//        String nomeArquivo = "relatorio-estoque.pdf";
//
//        List<Produto> produtos = List.of(
//                new Produto(1, "Teclado mecânico", "Periféricos", 15, 249.90),
//                new Produto(2, "Mouse sem fio", "Periféricos", 32, 89.90),
//                new Produto(3, "Monitor 24\"", "Monitores", 8, 899.00),
//                new Produto(4, "Notebook i5", "Computadores", 5, 3499.00),
//                new Produto(5, "Headset gamer", "Áudio", 12, 199.90)
//        );
//
//        // 1º passo: Thymeleaf preenche o template e devolve o HTML como String
//        GeradorHtml geradorHtml = new GeradorHtml();
//        String html = geradorHtml.gerarHtmlRelatorioEstoque(produtos);
//
//        // 2º passo: OpenHTMLtoPDF converte esse HTML em PDF
//        ConversorPdf conversorPdf = new ConversorPdf();
//        conversorPdf.converterHtmlParaPdf(html, nomeArquivo);
        S3Provider resposta = new S3Provider();

        //Adata precisa ser fornecida em ano/mes/dia
        S3Object objetos = resposta.listarObjetos(resposta.getBucket(), "30:56:0f:06:7a:88", "2026/11/24").getFirst();
        resposta.mediaMetricas(resposta, objetos);
    }


}
