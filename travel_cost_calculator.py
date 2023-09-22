



from csv import *

array_a = {}
array_b = {}
array_c = {}

def openAs_h(file):  
    with open(file) as h:
        r = reader(h)
        for row in r:
            openAs_h_a[row[0]] = float(row[1])

def OpenAs_e(file): 
    with open(file) as e:
        r = reader(e)
        for row in r:
            openAs_h_b[row[0].upper()] = float(row[1]) * 1 

def openAS_f(file):
    with open(file) as f:
        r = reader(f)
        for row in r:
            openAs_h_c[row[0]] = float(row[1])

def main():
    openAs_h('data/hotel_rates.csv')
    OpenAs_e('data/exchange_rates.csv')
    openAS_f('data/flight_costs.csv')

    upperClassDestination = input("Enter your destination: ").upper()

    f = openAs_h_c.get(upperClassDestination, 0.0)
    h = openAs_h_a.get(upperClassDestination, 0.0)

    days = int(input("Enter your stay duration in days: "))
    h *= days
    total = f + h

    print(f"Flight cost: USD {f:.2f}")
    print(f"Hotel cost for {days} days: USD {h:.2f}")
    print(f"Total: USD {total:.2f}")

    currency = input(f"Select your currency for final price estimation ({', '.join(b.keys())}): ")

    p = total * openAs_h_b[currency]
    print(f"Total in {currency}: {p:.2f}")

if __name__ == "__main__":
    main()
