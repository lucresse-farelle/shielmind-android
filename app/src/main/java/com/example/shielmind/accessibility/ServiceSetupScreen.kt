package com.example.shielmind.ui

// ════════════════════════════════════════════════════════
// À CRÉER DANS : app/src/main/java/com/example/shielmind/ui/ServiceSetupScreen.kt
//
// Écran Jetpack Compose simple pour activer le service.
// Membre 3 (UI) pourra ensuite l'embellir avec son design,
// la logique fonctionnelle est déjà prête ici.
// ════════════════════════════════════════════════════════

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.shielmind.accessibility.AccessibilityHelper

/**
 * Écran affiché tant que le service n'est pas activé.
 *
 * NOTE KOTLIN/COMPOSE : "@Composable" = annotation qui dit
 * "ceci est un morceau d'interface", similaire à un widget Flutter
 * si tu connais, ou un component React.
 */
@Composable
fun ServiceSetupScreen() {

    val context = LocalContext.current

    // "remember { mutableStateOf(...) }" = une variable qui, quand
    // elle change, redessine automatiquement l'écran.
    // C'est LE concept clé de Compose (équivalent useState en React)
    var isEnabled by remember {
        mutableStateOf(AccessibilityHelper.isAccessibilityServiceEnabled(context))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = if (isEnabled) "✅ Protection active" else "⚠️ Protection désactivée",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (isEnabled)
                "ShieldMind surveille les contenus en arrière-plan."
            else
                "Pour protéger votre enfant, activez le service d'accessibilité ShieldMind dans les paramètres Android.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (!isEnabled) {
            Button(onClick = {
                AccessibilityHelper.openAccessibilitySettings(context)
            }) {
                Text("Activer la protection")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Bouton de rafraîchissement : utile car après être revenu
        // des paramètres Android, on doit re-vérifier l'état
        OutlinedButton(onClick = {
            isEnabled = AccessibilityHelper.isAccessibilityServiceEnabled(context)
        }) {
            Text("Vérifier à nouveau")
        }
    }
}