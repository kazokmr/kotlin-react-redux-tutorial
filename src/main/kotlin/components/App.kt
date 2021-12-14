package components

import components.footer.footer
import components.header.header
import components.todos.todoList
import react.Props
import react.dom.div
import react.dom.h1
import react.dom.h2
import react.dom.main
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
            }
        }
        main {
            section(classes = "medium-container") {
                h2 {
                    +"Todos"
                }
                div(classes = "todoapp") {
                    // Header
                    header()
                    // TodoList
                    todoList()
                    // Footer
                    footer()
                }
            }
        }
    }
}