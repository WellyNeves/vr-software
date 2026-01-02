import 'dart:async';
import 'package:flutter/material.dart';

import '../controller/notification_controller.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final controller = TextEditingController();
  final notificacaoController = NotificationController();

  @override
  void initState() {
    super.initState();

    Timer.periodic(const Duration(seconds: 3), (_) async {
      await notificacaoController.atualizarStatus();
      setState(() {});
    });
  }

  Future<void> enviar() async {
    await notificacaoController.enviar(controller.text);
    controller.clear();
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Notificações")),
      body: Column(
        children: [
          TextField(controller: controller),
          ElevatedButton(
            onPressed: enviar,
            child: const Text("Enviar Notificação"),
          ),
          Expanded(
            child: ListView(
              children: notificacaoController.notificacoes.values.map((n) {
                return ListTile(
                  title: Text(n.mensagemId),
                  subtitle: Text(n.status),
                );
              }).toList(),
            ),
          ),
        ],
      ),
    );
  }
}