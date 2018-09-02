import Step from './components/Step'
import Container from './components/Container'
import Store from './components/store/Store'

export default [
  {
    path: "/",
    component: Container,
    children: [
      {
        path: "",
        component: Step
      }
    ]
  },
  {
    path: "/store",
    component: Store
  },
]
