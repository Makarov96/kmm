import SwiftUI
import shared

@main
@available(iOS 17.0, *)
struct iOSApp: App {
    init(){
      doInitKoin()
    }
	var body: some Scene {
       
		WindowGroup {
			ContentView()
		}
	}
}
