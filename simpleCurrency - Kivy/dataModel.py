import externalInterface as extInt

class model:
    def __init__(self):
        self.currencies = {}  # curr -> rate
        self.active_curr = (None, None)  # in and out curr
        self.enabled_curr = []  # to show on spinners