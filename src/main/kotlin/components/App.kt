package components

import components.footer.footer
import container.selectTodos
import container.todoAdded
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.main
import react.dom.html.ReactHTML.nav
import react.dom.html.ReactHTML.section

val app = FC<Props> {
    div {
        className = "App"
        nav {
            section {
                h1 {
                    +"Redux Fundamentals Example"
                }
            }
        }
        main {
            section {
                className = "medium-container"
                h2 {
                    +"Todos"
                }
                div {
                    className = "todoapp"
                    todoAdded()
                    selectTodos()
                    footer()
                }
            }
        }
    }
}