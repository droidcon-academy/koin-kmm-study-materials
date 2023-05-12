import SwiftUI
import shared
import OSLog

class UserModelController: ObservableObject {
    var module: UserModule = UserModuleWrapper().module
}

struct ContentView: View {
    @StateObject var state = UserModelController()
    @State private var username: String = ""

	var body: some View {
	    Text(String(describing: state.module.user))
		Text(state.module.isLoggedIn ? "Is logged in" : "Is Not logged in")
		Text(state.module.userName ?? "No user")
		Button(state.module.isLoggedIn ? "Log out" : "Login") {
		    if(state.module.isLoggedIn) {
		        state.module.logout()
		    }
		    else {
                state.module.login(userName: username)
		    }
            state.objectWillChange.send()
        }
        if(!state.module.isLoggedIn) {
            TextField(
                "User to login",
                text: $username
            ).disableAutocorrection(true)
            .textFieldStyle(.roundedBorder)
            .border(Color.blue)
            .fixedSize()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}