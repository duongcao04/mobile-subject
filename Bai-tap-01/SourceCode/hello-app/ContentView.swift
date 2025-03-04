//
//  ContentView.swift
//  hello-app
//
//  Created by Đào Viên on 4/3/25.
//

import SwiftUI 

struct ContentView: View {
    var body: some View {
        let avatarImg = Image("avatar")
        
        VStack {
            Text("Hi, Duong Cao!").font(.title)
        }
        VStack {
            avatarImg.resizable().frame(width: 200, height: 200).clipShape(Circle())
        }
        VStack {
            Text("University of Transport Ho Chi Minh City").font(.title3)
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
