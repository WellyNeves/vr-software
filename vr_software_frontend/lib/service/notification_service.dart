import 'dart:convert';
import 'package:http/http.dart' as http;

class NotificationService {
  static const String _baseUrl = "http://10.0.2.2:8080";

  Future<void> enviarMensagem(String id, String conteudo) async {
    await http.post(
      Uri.parse("$_baseUrl/api/notificar"),
      headers: {"Content-Type": "application/json"},
      body: jsonEncode({
        "mensagemId": id,
        "conteudoMensagem": conteudo,
      }),
    );
  }

  Future<String> consultarStatus(String id) async {
    final response = await http.get(
      Uri.parse("$_baseUrl/api/notificacao/status/$id"),
    );
    return response.body;
  }
}