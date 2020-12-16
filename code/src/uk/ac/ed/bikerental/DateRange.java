package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.function.BooleanSupplier;

/**
DateRange represents a duration spanning (inclusively) a start 
date and an end date, both represented by Java LocalDate objects.
@author Daniel Wilks
@author Eshaan Manglik
*/

public class DateRange {
    
    /**
    
    start keeps track of the start of the DateRange and
    end   keeps track of the end of the DateRange
    
    */
    private LocalDate start, end;

    /**
    
    Creates a instance of DateRange class.
    @param start     the starting date of the date range
    @param end       the end date of the date range
    */
    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
    
    public LocalDate getStart() {
        return this.start;
    }
    
    public LocalDate getEnd() {
        return this.end;
    }

    /**
    
    Calculates the date range in years and returns said number.
    @return     number of years between the start date and end date.
    */
    public long toYears() {
        return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
    }

    /**
    
    Calculates the date range in days and returns said number.
    @return     number of days between the start date and end date.
    */
    public long toDays() {
        return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
    }

    /**
    
    Determines whether there is an overlap between this date range and 
    another date range object.
    
    @param other    Date range that we compare with to see if there is
                    overlap.
    @return         True if there is an overlap between this date range 
                    and 'other' date range.
    */
    public Boolean overlaps(DateRange other) {
        // checking if 2 date ranges are equal
        if(this.start.equals(other.start) && this.end.equals(other.end))
            return true;
    	
        // checking if the start of first dateRange is overlapping with the other
        // dateRange
    	boolean startIsAfterOtherStart = this.start.isAfter(other.getStart());
    	boolean startIsBeforeOtherEnd = this.start.isBefore(other.getEnd());
    	boolean startOverlaps = startIsAfterOtherStart && startIsBeforeOtherEnd;
    	
        // checking if the end of first dateRange is overlapping with the other
        // dateRange
    	boolean endIsBeforeOtherEnd = this.end.isBefore(other.getEnd());
    	boolean endIsAfterOtherStart = this.end.isAfter(other.getStart());
    	boolean endOverlaps = endIsBeforeOtherEnd && endIsAfterOtherStart;    	
    	
    	boolean result1 = endOverlaps || startOverlaps;
    	
        // checking if one dateRange is contained in the other DateRange
    	boolean startBeforeOtherStart = this.start.isBefore(other.start);
    	boolean endBeforeOtherEnd = this.end.isAfter(other.end);
    	boolean result2 = startBeforeOtherStart && endBeforeOtherEnd;
    	
        return result1 || result2;
    }

    @Override
    public int hashCode() {

        return Objects.hash(end, start);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DateRange other = (DateRange) obj;
        return Objects.equals(end, other.end) && Objects.equals(start, other.start);
    }
}