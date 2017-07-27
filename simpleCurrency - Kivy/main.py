import kivy
import externalInterface as extInt

kivy.require('1.9.0')

from kivy.app import App
from kivy.uix.label import Label
from kivy.uix.widget import Widget
from kivy.uix.textinput import TextInput
from kivy.uix.gridlayout import GridLayout
from kivy.properties import ObjectProperty, ListProperty
from kivy.uix.spinner import Spinner

class calcView(Widget):
    in_curr = ObjectProperty(TextInput)
    out_curr = ObjectProperty(TextInput)
    update_btn = ObjectProperty(None)
    in_choose = Spinner()
    out_choose = Spinner()

    def __init__(self, **kwargs):
        # self.currencies = ['AUD', 'USD', 'NZD']
        self.currencies = extInt.get_currencies()
        super(calcView, self).__init__(**kwargs)
        self.in_choose.values = ['hello', 'bye']
        # self.in_choose.pos = (1000,1000)
        # self.in_curr.text = "Hello"
        # self.in_choose.text = "Bye"

    def update_rates(self):
        in_rate, out_rate = extInt.update_cl()
        if in_rate and out_rate:
            pass




    # in_curr.text = "Hello world"


class ViewApp(App):

    def build(self):
        return calcView()


if __name__ == '__main__':
    app = ViewApp()
    app.run()
