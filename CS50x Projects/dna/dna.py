import csv
import sys


def main():
    # TODO: Check for command-line usage
    if len(sys.argv) != 3:
        print("Missing command-line argument")
        exit()

    # TODO: Read database file into a variable
    file = sys.argv[1]
    with open(file) as f:
        reader = csv.DictReader(f)
        # Create gene database
        genes = list(reader)

    # TODO: Read DNA sequence file into a variable
    file = sys.argv[2]
    with open(file, "r") as f:
        # Since there is only one line, just reads that line into variable sec
        seq = f.readline()

    # TODO: Find longest match of each STR in DNA sequence
    # Results is dict for the longest_match function calls
    results = {}
    # Goes over seq and outputs number of matches for each type of DNA, which are in genes[0]
    for i in genes[0]:
        results[i] = longest_match(seq, i)

    # TODO: Check database for matching profiles
    # Counter detects if the match count of person and seq line up, meaning the seq is from the person
    cnt = 1
    # Default state of person in case there is not a match found
    person = "No match"
    # Goes through rows
    for i in range(len(genes)):
        # Goes through columns
        for j in results:
            # Detects when match counts line up, adding to count
            if str(results[j]) == genes[i][j]:
                cnt += 1
        # Detects when ALL the seq match counts line up, meaning person found
        if cnt == len(results):
            # Declare person as name found in table that matches seq counts
            person = genes[i]["name"]
            break
        else:
            # Reset count in case of no find
            cnt = 1
    # Prints either name or default case depending on result of func
    print(person)
    return


def longest_match(sequence, subsequence):
    """Returns length of longest run of subsequence in sequence."""

    # Initialize variables
    longest_run = 0
    subsequence_length = len(subsequence)
    sequence_length = len(sequence)

    # Check each character in sequence for most consecutive runs of subsequence
    for i in range(sequence_length):
        # Initialize count of consecutive runs
        count = 0

        # Check for a subsequence match in a "substring" (a subset of characters) within sequence
        # If a match, move substring to next potential match in sequence
        # Continue moving substring and checking for matches until out of consecutive matches
        while True:
            # Adjust substring start and end
            start = i + count * subsequence_length
            end = start + subsequence_length

            # If there is a match in the substring
            if sequence[start:end] == subsequence:
                count += 1

            # If there is no match in the substring
            else:
                break

        # Update most consecutive matches found
        longest_run = max(longest_run, count)

    # After checking for runs at each character in seqeuence, return longest run found
    return longest_run


main()
