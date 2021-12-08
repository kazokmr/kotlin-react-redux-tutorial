package components

import react.Props
import react.dom.div
import react.dom.h1
import react.dom.h2
import react.dom.nav
import react.dom.section
import react.fc

val app = fc<Props> {
    div(classes = "App") {
        nav {
            section {
                h1 {
                    +"Redux Fundamentals Example"
                }
                div(classes = "navContents") {
                    div(classes = "navLinks") { }
                }
            }
            section {
                h2 {
                    +"Welcome to the Redux Fundamentals example app!"
                }
            }
        }
    }
}