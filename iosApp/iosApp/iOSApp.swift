import SwiftUI
import Firebase
import ComposeApp

@main
struct iOSApp: App {
    init(){
        AppModuleKt.doInitKoin()
        FirebaseApp.configure()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
                .ignoresSafeArea(edges: .all)
                .ignoresSafeArea(.keyboard)
        }
    }
}
