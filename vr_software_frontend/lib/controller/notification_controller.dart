import 'dart:async';
import 'package:uuid/uuid.dart';

import '../model/notification_model.dart';
import '../service/notification_service.dart';

class NotificationController {
  final _uuid = const Uuid();
  final _service = NotificationService();

  final Map<String, NotificationModel> notificacoes = {};

  Future<void> enviar(String conteudo) async {
    final id = _uuid.v4();

    notificacoes[id] = NotificationModel(
      mensagemId: id,
      status: "AGUARDANDO PROCESSAMENTO",
    );

    await _service.enviarMensagem(id, conteudo);
  }

  Future<void> atualizarStatus() async {
    for (var item in notificacoes.values) {
      final status = await _service.consultarStatus(item.mensagemId);
      item.status = status;
    }
  }
}