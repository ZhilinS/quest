import Step from './components/container/step/Step'
import Container from './components/container/Container'
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
